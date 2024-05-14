package com.example.blog.app.apis.repositories;

import com.example.blog.app.apis.entities.Category;
import com.example.blog.app.apis.entities.Post;
import com.example.blog.app.apis.entities.User;
import com.example.blog.app.apis.payloads.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

//    List<PostDto> findByTitleContaining(String keyword);
}
