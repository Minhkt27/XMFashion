package com.project.xm.dto.request.orderDetailRequest;

import com.project.xm.model.Order;
import com.project.xm.model.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDetailUpdate {
    private int quantity;
}
