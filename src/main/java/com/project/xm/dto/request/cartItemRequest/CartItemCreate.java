package com.project.xm.dto.request.cartItemRequest;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CartItemCreate {
    private Long cartID;
    private Long productID;
    private Integer quantity;
}
