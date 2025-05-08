package com.project.xm.dto.request.productRequest;

import com.project.xm.model.OrderDetail;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProductCreate {

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
