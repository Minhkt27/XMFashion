package com.project.xm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
@Table(name = "PROMOTION")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promotionID;

    @NotBlank(message = "Code cannot be blank")
    @Column(name = "code", length = 50, nullable = false, unique = true)
    private String code;

    @Column(name = "description")
    private String description;

    @NotBlank(message = "Discount type is required")
    @Column(name = "applyFor", nullable = false)
    private String applyFor;

    @Positive(message = "Discount value must be greater than 0")
    @Column(name = "discountValue", nullable = false)
    private float discountValue;

    @PositiveOrZero(message = "Minimum order value must be >= 0")
    @Column(name = "minOrderValue", nullable = false)
    private Long minOrderValue;

    @PositiveOrZero(message = "Maximum discount must be >= 0")
    @Column(name = "maxDiscount", nullable = false)
    private Long maxDiscount;

    @FutureOrPresent(message = "Start date must be in the present or future")
    @Column(name = "startDate", nullable = false)
    private LocalDateTime startDate;

    @Future(message = "End date must be in the future")
    @Column(name = "endDate", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Promotion() {
    }
}
