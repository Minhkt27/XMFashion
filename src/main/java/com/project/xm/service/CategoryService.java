package com.project.xm.service;

import com.project.xm.dto.request.categoryRequest.CategoryCreate;
import com.project.xm.dto.request.categoryRequest.CategoryUpdate;
import com.project.xm.model.Category;
import com.project.xm.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }
    public Category getByid(Long id){
        return categoryRepository.findById(id).orElseThrow(()->new RuntimeException("Category not found."));
    }
    public Category getByName(String name){
        if (!categoryRepository.existsByName(name))
            throw new RuntimeException("Category not found");
        return categoryRepository.findByName(name);
    }
    public Category createRequest(CategoryCreate request){
        Category category=new Category();
        if(categoryRepository.existsByName(request.getName()))
            throw new RuntimeException("Category existed");
        category.setName(request.getName());
        return categoryRepository.save(category);
    }

    public Category updateRequest(Long id, CategoryUpdate request){
        if(!categoryRepository.existsById(id))
            throw new RuntimeException("Category is not exists");
        Category category= getByid(id);
        category.setName(request.getName());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id){
        if(!categoryRepository.existsById(id))
            throw new RuntimeException("Category isn't existed");
        categoryRepository.deleteById(id);
    }

}
