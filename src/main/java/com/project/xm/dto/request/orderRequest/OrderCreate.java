package com.project.xm.dto.request.orderRequest;

import com.project.xm.dto.request.orderDetailRequest.OrderDetailCreate;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class OrderCreate {
    private Long id;

    private Long userId;

    private BigDecimal totalPrice; // Đảm bảo giá trị không âm

    private String status;

    private List<OrderDetailCreate> orderDetailCreates;

}
