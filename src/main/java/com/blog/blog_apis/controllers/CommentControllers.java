package com.blog.blog_apis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog_apis.payloads.ApiResponse;
import com.blog.blog_apis.payloads.CommentDto;
import com.blog.blog_apis.services.CommentServices;


@RestController
@RequestMapping("/api")
public class CommentControllers {

    @Autowired
    private CommentServices commentServices;

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
    @PathVariable Integer postId){
        CommentDto commentDto2 = this.commentServices.createComment(commentDto, postId);
        return new ResponseEntity<CommentDto>(commentDto2,HttpStatus.CREATED);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentServices.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Succesfully!!..",true),HttpStatus.OK);
    }
}
