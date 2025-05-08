package com.project.xm.controller;

import com.project.xm.dto.request.categoryRequest.CategoryCreate;
import com.project.xm.dto.request.categoryRequest.CategoryUpdate;
import com.project.xm.model.Category;
import com.project.xm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Category createCategory(@RequestBody CategoryCreate request){
        return categoryService.createRequest(request);
    }
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable("id") Long id, @RequestBody CategoryUpdate request){
        return categoryService.updateRequest(id,request);
    }
    @GetMapping("/{id}")
    public Category getCategory(@PathVariable("id") Long id){
        return categoryService.getByid(id);
    }
    @GetMapping("/name/{name}")
    public Category getByName(@PathVariable("name") String name){
        return categoryService.getByName(name);
    }
    @GetMapping
    public List<Category> getAll(){
        return categoryService.getAll();
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return "Category has been deleted";
    }
}
