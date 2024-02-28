package com.example.blog.app.apis.services;

import com.example.blog.app.apis.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    //    CREATE
    CategoryDto createCategory(CategoryDto categotyDto);

    //    UPDATE
    CategoryDto updateCategory(CategoryDto categotyDto , Integer id);

    //    GET
    CategoryDto getCategory(Integer id);

    //    GETALL
    List<CategoryDto> getAllCategory();

    //    DELETE
    void deleteCategory(Integer id);
}


