package com.example.blog.app.apis.services;

import com.example.blog.app.apis.payloads.CommentDto;
import jakarta.persistence.criteria.CriteriaBuilder;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Integer Postid);

    void deleteComment(Integer id);
}
