package com.project.xm.service;

import com.project.xm.dto.request.productRequest.ProductCreate;
import com.project.xm.dto.request.productRequest.ProductUpdate;
import com.project.xm.model.Category;
import com.project.xm.model.Product;
import com.project.xm.repository.CategoryRepository;
import com.project.xm.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getAll(){
        return productRepository.findAll();
    }
    public List<Product> getByCategoryId(Long id){
        if(productRepository.existsByCategory_CategoryID(id))
            throw new RuntimeException("Category ID not found");
        return productRepository.findByCategory_CategoryID(id);
    }
    public Product getById(Long id){
        return productRepository.findById(id).orElseThrow(()->new RuntimeException("Products not found"));
    }
    public List<Product> getByLikeName(String name){
        return productRepository.findByNameContaining(name);
    }

    public List<Product> getBySize(String size){
        return productRepository.findBySize(size);
    }

    public List<Product> getByColor(String color){
        return productRepository.findByColor(color);
    }

    // Tạo sản phẩm mới
    @Transactional
    public Product createRequest(ProductCreate request) {
        if (productRepository.existsByName(request.getName())) {
            throw new RuntimeException("Product already exists with name: " + request.getName());
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(BigDecimal.valueOf(request.getPrice()))
                .size(request.getSize())
                .color(request.getColor())
                .quantity(request.getQuantity())
                .collectionID(request.getCollectionId())
                .category(category)
                .build();

        return productRepository.save(product);
    }
    // Cập nhật sản phẩm
    @Transactional
    public Product updateRequest(Long id, ProductUpdate request) {
        Product product = getById(id);

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(BigDecimal.valueOf(request.getPrice()));
        product.setSize(request.getSize());
        product.setColor(request.getColor());
        product.setQuantity(request.getQuantity());
        product.setCollectionID(request.getCollectionId());
        product.setCategory(category);

        return productRepository.save(product);
    }

    public void deleteRequest(Long id){
        if(!productRepository.existsById(id))
            throw new RuntimeException("Product is not exists");
        productRepository.deleteById(id);
    }
}
