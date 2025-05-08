package com.project.xm.repository;

import com.project.xm.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCart_CartIDAndProduct_ProductID(Long cartId, Long productId);  // Cập nhật tên trường
    List<CartItem> findAllByCart_CartID(Long cartId);  // Cập nhật tên trường
    void deleteByCart_CartIDAndProduct_ProductID(Long cartId, Long productId);
}
