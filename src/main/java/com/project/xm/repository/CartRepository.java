package com.project.xm.repository;

import com.project.xm.model.Cart;
import com.project.xm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    boolean existsByUser_UserID(Long userID);
    Optional<Cart> findByUser_UserID(Long userId);

    Cart findByUser(User user);
}
