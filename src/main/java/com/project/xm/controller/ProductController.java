package com.project.xm.controller;

import com.project.xm.dto.request.productRequest.ProductCreate;
import com.project.xm.dto.request.productRequest.ProductUpdate;
import com.project.xm.model.Product;
import com.project.xm.service.ProductService;
import jakarta.validation.Valid;
import org.hibernate.dialect.function.LpadRpadPadEmulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAll(){
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getByID(@PathVariable Long id){
        return productService.getById(id);
    }
    @GetMapping("/category/{id}")
    public List<Product> getByCategoryId(@PathVariable("id") Long id){
        return productService.getByCategoryId(id);
    }

    @GetMapping("/name/{name}")
    public List<Product> getByLikeName(@PathVariable("name") String name){
        return productService.getByLikeName(name);
    }

    @GetMapping("/color/{color}")
    public List<Product> getByName(@PathVariable("color") String color){
        return productService.getByColor(color);
    }

    @GetMapping("/Size/{size}")
    public List<Product> getBySize(@PathVariable("size") String size){
        return productService.getBySize(size);
    }

    @PostMapping
    public Product createProduct(@RequestBody @Valid ProductCreate request){
        return productService.createRequest(request);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody ProductUpdate request){
        return productService.updateRequest(id,request);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        productService.deleteRequest(id);
        return "Product has been deleted";
    }


}
