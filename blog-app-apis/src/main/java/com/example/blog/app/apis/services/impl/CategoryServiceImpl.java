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

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        return categorytoCategoryDto(categoryRepo.save(categoryDtotoCategory(categoryDto)));
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(()-> new ResourseNotFoundExecption("Category", "id",id));
        category.setCategoryDesc(categoryDto.getCategoryDesc());
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        Category savedCategory = categoryRepo.save(category);
        return categorytoCategoryDto(savedCategory);
    }

    @Override
    public CategoryDto getCategory(Integer id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(()-> new ResourseNotFoundExecption("Category", "id", id));
        return categorytoCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> allCategory = categoryRepo.findAll();
        List<CategoryDto> allCategoryDto = new ArrayList<>();
        for(Category i:allCategory){
            allCategoryDto.add(categorytoCategoryDto(i));
        }
        return allCategoryDto;
    }

    @Override
    public void deleteCategory(Integer id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(()-> new ResourseNotFoundExecption("Category", "id", id));
        categoryRepo.delete(category);
    }

    Category categoryDtotoCategory(CategoryDto categoryDto){
        return modelMapper.map(categoryDto,Category.class);
    }

    CategoryDto categorytoCategoryDto(Category category){
        return modelMapper.map(category,CategoryDto.class);
    }
}
