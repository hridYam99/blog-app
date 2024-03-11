package com.example.blog.app.apis.repositories;

import com.example.blog.app.apis.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Integer> {
}
