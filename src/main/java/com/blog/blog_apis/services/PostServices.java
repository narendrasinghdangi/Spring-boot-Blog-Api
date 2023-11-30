package com.blog.blog_apis.services;

import java.util.List;
import com.blog.blog_apis.payloads.PostDto;
import com.blog.blog_apis.payloads.PostResponse;

public interface PostServices {
    
    PostDto createPost(PostDto PostDto,Integer UserId,Integer CategoryId);
    PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postId);
    PostDto getPostById(Integer postId);
    PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);

    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> searchPosts(String keyword);
}
