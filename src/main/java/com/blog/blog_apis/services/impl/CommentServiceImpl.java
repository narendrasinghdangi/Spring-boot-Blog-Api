package com.blog.blog_apis.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog_apis.entities.Comment;
import com.blog.blog_apis.entities.Post;
import com.blog.blog_apis.exceptions.ResourceNotFoundException;
import com.blog.blog_apis.payloads.CommentDto;
import com.blog.blog_apis.repositories.CommentRepo;
import com.blog.blog_apis.repositories.PostRepo;
import com.blog.blog_apis.services.CommentServices;

@Service
public class CommentServiceImpl implements CommentServices{


    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepo postRepo;


    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

    Post post = this.postRepo.findById(postId)
    .orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));

    Comment comment = this.modelMapper.map(commentDto, Comment.class);
    comment.setPost(post);
    Comment savedComment = this.commentRepo.save(comment);

    return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId)
        .orElseThrow(()-> new ResourceNotFoundException("Comment", "CommentID", commentId));
        this.commentRepo.delete(comment);
    }

    
}
