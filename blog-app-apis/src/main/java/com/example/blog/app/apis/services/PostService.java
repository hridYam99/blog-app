package com.example.blog.app.apis.services;

import com.example.blog.app.apis.repositories.PostRepo;

import java.util.List;

public interface PostService {
//    GET
    PostRepo getPost();
//    GET ALL
    List<PostRepo> getAllPost();
//    DELETE
    void deletePost();
//    CREATE
    PostRepo createPost(PostRepo postRepo);
//    UPDATE
    PostRepo updatePost(PostRepo postRepo);
}
