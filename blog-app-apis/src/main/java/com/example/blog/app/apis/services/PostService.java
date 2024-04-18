package com.example.blog.app.apis.services;

import com.example.blog.app.apis.payloads.PostDto;
import com.example.blog.app.apis.repositories.PostRepo;

import java.util.List;

public interface PostService {
//    GET POST BY ID
    PostDto getPost(Integer id);
//    GET POST BY USER
    PostDto getPostByUser(Integer id);
//    GET POST BY CATEGORY
    PostDto getPostByCategoty(Integer id);
//    SEARCH POST BY KEYWORD
    PostDto searchPost(String keyword);
//    GET ALL
    List<PostDto> getAllPost();
//    DELETE
    void deletePost();
//    CREATE
    PostDto createPost(PostDto postDto);
//    UPDATE
    PostDto updatePost(PostDto postDto);
}
