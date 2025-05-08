package com.project.xm.dto.request.productRequest;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductUpdate {
    private String name;

    private String description;

    private int price;

    private String size;

    private String color;

    private String image;

    private Long quantity;

    private Long categoryId;

    private Long collectionId;

}
