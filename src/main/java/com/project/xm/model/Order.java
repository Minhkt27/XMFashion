package com.project.xm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
@Table(name = "`ORDER`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @PositiveOrZero(message = "Total price must be >= 0")
    @Column(name = "totalPrice", nullable = false)
    private BigDecimal totalPrice; // Đảm bảo giá trị không âm

    @NotBlank(message = "Order status is required")
    @Column(name = "status", nullable = false)
    private String status;

    // Liên kết với OrderItem (một đơn hàng có thể có nhiều sản phẩm)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetail;


    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Order() {
    }
}
