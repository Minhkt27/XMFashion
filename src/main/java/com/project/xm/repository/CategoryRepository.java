package com.project.xm.repository;

import com.project.xm.model.Category;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean existsByName(String name);
    Category findByName(String name);
    List<Category> findByOrderByCreatedAtDesc();
    List<Category> findAll(Sort sort);
}
