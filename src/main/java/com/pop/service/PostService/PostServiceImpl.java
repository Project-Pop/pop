package com.pop.service.PostService;

import com.pop.common.Response;
import com.pop.dao.PostsDao;
import com.pop.dto.*;
import com.pop.models.*;
import com.pop.service.AwsClientService.DynamoDBService;
import com.pop.service.AwsClientService.SqsService;
import com.pop.service.AwsClientService.StorageService;
import com.pop.service.NotificationService.NotificationService;
import com.pop.utils.MediaFilenameBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostsDao postsDao;

    @Autowired
    private StorageService storageService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SqsService sqsService;

    @Autowired
    private DynamoDBService dynamoDBService;

    private boolean amITheOwnerOfThisPost(String postId) {
        var principalUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principalUser.getUsername();
        return (username == postsDao.getOwnerOfPost(postId));
    }


    @Override
    public Response getHomeFeed() {
        var principalUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principalUser.getUsername();

        List<FeedItem> feed = dynamoDBService.fetchUserFeed(username);
        return new Response(feed, "User home feed", HttpServletResponse.SC_OK);
    }

    @Override
    public Response createPost(NewPostDto newPostDto, MultipartFile hdVideo, MultipartFile thumbVideo) {

        String principalUsername = ((JwtUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal())
                .getUsername();

        try {
            Posts newPost = new Posts(
                    UUID.randomUUID().toString(),
                    null,
                    principalUsername,
                    newPostDto.getDescription(),
                    0,
                    new Date(),
                    0
            );
            String postHDVideoFilename = MediaFilenameBuilder.buildPostMediaFilename(newPost.getPostId());
            String postHDVideoUrl = storageService.uploadFile(hdVideo, postHDVideoFilename);
            newPost.setImageUrl(postHDVideoUrl);

            newPost.setTaggedUsers(newPostDto.getTaggedUsers().stream().map(usernameDto -> new Tagged(usernameDto.getUsername(), newPost.getPostId())).collect(Collectors.toList()));
            postsDao.createPost(newPost);

            // sending tagged notification to all tagged users
//            notificationService.buildTagRequestNotification(
//                    newPostDto.getTaggedUsers(),
//                    newPost.getPostId(),
//                    newPost.getImageUrl()
//            );

            // sending this post for feed generation
//            sqsService.sendPostForFeedGeneration(principalUsername, newPost.getPostId(), newPost.getImageUrl(), newPost.getTimestamp());

            return new Response(newPost, "post created successfully", HttpServletResponse.SC_CREATED);

        } catch (Exception err) {
            return Response.error(err.toString(), HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    public Response getUserPosts(String username) {
        try {
            List<Posts> posts = postsDao.getMyTaggedPostsByUsername(username);
            return new Response(posts, "posts with tag of this user", HttpServletResponse.SC_OK);

        } catch (Exception err) {
            return Response.error(err.toString(), HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    public Response getUserUploads(String username) {
        try {
            List<Posts> posts = postsDao.getPostUploadedByUsername(username);
            return new Response(posts, "posts uploaded by this user", HttpServletResponse.SC_OK);
        } catch (Exception err) {
            return Response.error(err.toString(), HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    public Response getPostDetails(String postId) {
        try {
            // if I am not the owner of post, then only approved tagged users are fetched
            Posts post = postsDao.getPostByPostId(postId, amITheOwnerOfThisPost(postId) == false, ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
            return new Response(post, "Post fetched", HttpServletResponse.SC_OK);
        } catch (Exception err) {
            return Response.error(err.toString(), HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    public Response deletePost(String postId) {
        try {
            if (amITheOwnerOfThisPost(postId)) {
                postsDao.deletePost(postId);
                return Response.ok("Post deleted successfully", HttpServletResponse.SC_ACCEPTED);
            } else {
                return Response.error("You can not delete this post", HttpServletResponse.SC_FORBIDDEN);
            }

        } catch (Exception err) {
            return Response.error(err.toString(), HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    public Response editPostDetails(String postId, PatchPostDto patchPostDto) {
        try {
            if (amITheOwnerOfThisPost(postId)) {

                postsDao.editDescription(postId, patchPostDto.getDescription());

                return Response.ok("Post edited successfully", HttpServletResponse.SC_ACCEPTED);
            } else {
                return Response.error("You can not edit this post", HttpServletResponse.SC_FORBIDDEN);
            }

        } catch (Exception err) {
            return Response.error(err.toString(), HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    public Response tagApproval(String postId, String username, TagApprovalDto tagApprovalDto) {
        try {

            boolean status = tagApprovalDto.isApproved();

            if (status) {
                postsDao.acceptPost(postId, username);
            } else {
                postsDao.declinePost(postId, username);
            }

            //sending notification
            NotificationResponseType notificationResponseType = status ? NotificationResponseType.accept : NotificationResponseType.deny;
            notificationService.buildTagResponseNotification(
                    postsDao.getOwnerOfPost(postId),
                    notificationResponseType,
                    postId,
                    storageService.getMediaUrlFromFilename(MediaFilenameBuilder.buildPostMediaFilename(postId))
            );

            String responseMessage = status ? "Accepted" : "Denied";
            return Response.ok(responseMessage, HttpServletResponse.SC_ACCEPTED);

        } catch (Exception err) {
            return Response.error(err.toString(), HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    public Response removeTag(String postId, String username) {
        try {
            if (amITheOwnerOfThisPost(postId)) {
                postsDao.removeFromTaggedPost(username, postId);
                return Response.ok("removed tag", HttpServletResponse.SC_ACCEPTED);
            } else {
                return Response.error("You can't remove this tag", HttpServletResponse.SC_FORBIDDEN);
            }
        } catch (Exception err) {
            return Response.error(err.toString(), HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    public Response reactOnPost(String postId, PostReactionDto postReactionDto) {
        try {
            var principalUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = principalUser.getUsername();

            if (postReactionDto.getReactionString() != null) {

                postsDao.reactToPost(username, postReactionDto.getReactionString(), postId);

                // sending notification
                notificationService.buildPostReactionNotification(
                        postsDao.getOwnerOfPost(postId),
                        postId,
                        storageService.getMediaUrlFromFilename(MediaFilenameBuilder.buildPostMediaFilename(postId)),
                        postsDao.getOwnerOfPost(postId));

                return Response.ok("Reacted", HttpServletResponse.SC_CREATED);
            } else {
                postsDao.deleteReaction(postId, username);
                return Response.ok("Reaction Deleted", HttpServletResponse.SC_ACCEPTED);
            }
        } catch (Exception err) {
            return Response.error(err.toString(), HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
