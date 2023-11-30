package com.blog.blog_apis.services;

import com.blog.blog_apis.payloads.CommentDto;

public interface CommentServices {
    CommentDto createComment(CommentDto commentDto, Integer postId);

    void deleteComment(Integer commentId);
}
