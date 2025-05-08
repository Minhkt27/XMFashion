package com.project.xm.repository;

import com.project.xm.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    boolean existsByOrder_OrderID(Long orderID);  // Sửa thành 'order_Id' để khớp với 'id' trong 'Order'
    List<OrderDetail> findByOrder_OrderID(Long orderID);  // Đảm bảo 'Order' có trường 'id'
    List<OrderDetail> findByProduct_ProductID(Long productID);  // Đảm bảo 'Product' có trường 'id'
}
