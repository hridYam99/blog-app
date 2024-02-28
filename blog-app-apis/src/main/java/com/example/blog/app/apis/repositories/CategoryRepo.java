package com.example.blog.app.apis.repositories;


import com.example.blog.app.apis.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
