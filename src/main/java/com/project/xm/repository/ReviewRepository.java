package com.project.xm.repository;

import com.project.xm.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    boolean existsByProduct_ProductID(Long productId);  // Truy vấn vào productId của Product

    boolean existsByUser_UserID(Long userId);  // Sửa lại để khớp với tên khóa chính trong User

    boolean existsByRating(int rating);

    List<Review> findByProduct_ProductID(Long productId);  // Truy vấn đúng vào productId

    List<Review> findByRating(int rating);
}
