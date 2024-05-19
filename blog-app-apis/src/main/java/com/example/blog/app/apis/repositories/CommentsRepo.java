package com.example.blog.app.apis.repositories;

import com.example.blog.app.apis.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepo extends JpaRepository<Comment,Integer> {

}
