package com.project.xm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
@Table(name = "ORDER_DETAIL")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailID;

    // Liên kết với Order (một đơn hàng có thể có nhiều sản phẩm)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderID", nullable = false)
    private Order order;

    // Liên kết với Product (mỗi sản phẩm thuộc về một đơn hàng)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productID", nullable = false)
    private Product product;

    @PositiveOrZero(message = "Quantity must be >= 0")
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @PositiveOrZero(message = "Price must be >= 0")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    public OrderDetail() {
    }
}
