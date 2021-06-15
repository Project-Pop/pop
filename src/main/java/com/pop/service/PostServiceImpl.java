package com.pop.service;

import com.pop.common.Response;
import com.pop.dao.PostsDao;
import com.pop.dto.NewPostDto;
import com.pop.dto.UsernameDto;
import com.pop.models.Posts;
import com.pop.models.Tagged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class PostServiceImpl implements PostService {

    @Autowired
    private PostsDao postsDao;

    @Override
    public Response createPost(NewPostDto newPostDto) {

        String principalUsername = ((UsernameDto) SecurityContextHolder.getContext()
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
            newPost.setTaggedUsers(newPostDto.getTaggedUsers().stream().map(usernameDto-> new Tagged(usernameDto.getUsername(),newPost.getPostId())).collect(Collectors.toList()));
            postsDao.createPost(newPost);
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
            Posts post = postsDao.getPostByPostId(postId, false);
            return new Response(post,"Post fetched",HttpServletResponse.SC_OK);
        } catch (Exception err) {
            return Response.error(err.toString(), HttpServletResponse.SC_BAD_REQUEST);
        }
    }


}
