package com.example.blog.app.apis.services.impl;

import com.example.blog.app.apis.entities.Comment;
import com.example.blog.app.apis.entities.Post;
import com.example.blog.app.apis.execptions.ResourseNotFoundExecption;
import com.example.blog.app.apis.payloads.CommentDto;
import com.example.blog.app.apis.payloads.PostDto;
import com.example.blog.app.apis.repositories.CommentsRepo;
import com.example.blog.app.apis.repositories.PostRepo;
import com.example.blog.app.apis.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    CommentsRepo commentsRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->
                new ResourseNotFoundExecption("Post", "postId", postId));

        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);

        CommentDto savedComment = modelMapper.map(commentsRepo.save(comment),CommentDto.class);

        return savedComment;
    }

    @Override
    public void deleteComment(Integer commentId) {
        commentsRepo.delete(commentsRepo.findById(commentId).orElseThrow(()->
                new ResourseNotFoundExecption("COmment", "commentId", commentId)));
    }
}
