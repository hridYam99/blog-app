package com.example.blog.app.apis.controllers;

import com.example.blog.app.apis.payloads.ApiResponse;
import com.example.blog.app.apis.payloads.CommentDto;
import com.example.blog.app.apis.services.CommentService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("create/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto,
            @PathVariable Integer postId){
        return new ResponseEntity<>(commentService.createComment(commentDto,postId), HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{postId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer postId){
        commentService.deleteComment(postId);
        return new ResponseEntity<>(new ApiResponse("Message delete successfully", true),HttpStatus.OK);
    }
}
