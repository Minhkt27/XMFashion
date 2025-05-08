package com.project.xm.dto.request.promotionRequest;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter

public class PromoUpdate {

    private String description;

    private String applyFor;

    private float discountValue;

    private Long minOrderValue;

    private Long maxDiscount;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private boolean isActive;


}
