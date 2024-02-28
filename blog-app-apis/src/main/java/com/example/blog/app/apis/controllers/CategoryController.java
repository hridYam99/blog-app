package com.example.blog.app.apis.controllers;

import com.example.blog.app.apis.payloads.ApiResponse;
import com.example.blog.app.apis.payloads.CategoryDto;
import com.example.blog.app.apis.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/{userId}")
    ResponseEntity<CategoryDto> getCategoryById(@PathVariable("userId") Integer id){
        CategoryDto categoryDto = categoryService.getCategory(id);
        return new ResponseEntity<>(categoryDto, HttpStatus.FOUND);
    }

    @GetMapping("/")
    ResponseEntity<List<CategoryDto>> getAllCategory(){
        return new ResponseEntity<>(categoryService.getAllCategory(),HttpStatus.FOUND);
    }

    @PostMapping("/")
    ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(categoryService.createCategory(categoryDto),HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<ApiResponse> deleteCategory (@PathVariable("userId") Integer id){
        return new ResponseEntity<>(new ApiResponse("Category deleted", true),HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable("userId") Integer id){
        return new ResponseEntity<>(categoryService.updateCategory(categoryDto,id),HttpStatus.OK);
    }
}
