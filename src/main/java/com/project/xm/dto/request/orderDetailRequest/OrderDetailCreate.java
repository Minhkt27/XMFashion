package com.project.xm.dto.request.orderDetailRequest;

import com.project.xm.model.Order;
import com.project.xm.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class OrderDetailCreate {
    private Long orderId;
    private Long productId;
    private int quantity;
    private BigDecimal price;

}
