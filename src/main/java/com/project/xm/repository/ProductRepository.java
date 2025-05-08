package com.project.xm.repository;

import com.project.xm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    boolean existsByName(String name);
    boolean existsByCategory_CategoryID(Long categoryID);  // Thay đổi từ existsByCategoryId
    boolean existsBySize(String size);
    boolean existsByColor(String color);
    List<Product> findByNameContaining(String keyword);
    List<Product> findByCategory_CategoryID(Long categoryID);  // Sửa lại truy vấn
    List<Product> findByPrice(int price);
    List<Product> findBySize(String size);
    List<Product> findByColor(String color);

}
