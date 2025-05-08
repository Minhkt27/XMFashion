package com.project.xm.repository;

import com.project.xm.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsByUser_UserID(Long userID);
    List<Order> findByUser_UserID(Long userID);

}
