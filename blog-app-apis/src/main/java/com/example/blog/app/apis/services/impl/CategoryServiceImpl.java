package com.example.blog.app.apis.services.impl;

import com.example.blog.app.apis.entities.Category;
import com.example.blog.app.apis.execptions.ResourseNotFoundExecption;
import com.example.blog.app.apis.payloads.ApiResponse;
import com.example.blog.app.apis.payloads.CategoryDto;
import com.example.blog.app.apis.repositories.CategoryRepo;
import com.example.blog.app.apis.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        categoryRepo.save(modelMapper.map(categoryDto, Category.class));
        return null;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(()-> new ResourseNotFoundExecption("Category", ""));
        return null;
    }

    @Override
    public CategoryDto getCategory(Integer id) {
        return null;
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        return null;
    }

    @Override
    public void deleteCategory(Integer id) {

    }
}
