package com.project.xm.dto.request.orderRequest;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderUpdate {
    private int totalPrice; // Đảm bảo giá trị không âm

    private String status;

}
