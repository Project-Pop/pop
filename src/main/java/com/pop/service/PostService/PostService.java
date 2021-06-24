package com.pop.service.PostService;

import org.springframework.web.multipart.MultipartFile;

import com.pop.common.Response;
import com.pop.dto.*;

public interface PostService {

//    public Response getGlobalFeed();

    public Response createPost(NewPostDto newPostDto, MultipartFile normalImage, MultipartFile miniImage);

//    public Response getHomeFeed();

    public Response getUserPosts(String username);

    public Response getUserUploads(String username);

    public Response getPostDetails(String postId);

    public Response editPostDetails(String postId, PatchPostDto patchPostDto);

    public Response deletePost(String postId);

    public Response tagApproval(String postId, String username, TagApprovalDto tagApprovalDto);

    public Response removeTag(String postId, String username);

    public Response reactOnPost(String postId, PostReactionDto postReactionDto);

}
