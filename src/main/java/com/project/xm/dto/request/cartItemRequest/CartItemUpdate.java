package com.project.xm.dto.request.cartItemRequest;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemUpdate {
    private Long cartID;
    private Long productID;
    private Integer quantity;
}
