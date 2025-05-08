package com.project.xm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;

    @NotBlank(message = "Product name is required")
    @Size(max = 45)
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @NotBlank(message = "Description is required")
    @Size(max = 255)
    @Column(name = "description", nullable = false)
    private String description;

    @PositiveOrZero(message = "Price must be >= 0")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    // Danh sách hình ảnh liên kết đến Product
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> images;

    @NotNull(message = "Size is required")
    @Column(name = "size", nullable = false)
    private String size;

    @NotNull(message = "Color is required")
    @Column(name = "color", nullable = false)
    private String color;

    @PositiveOrZero(message = "Quantity must be >= 0")
    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "collectionID")
    private Long collectionID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryID", nullable = false)
    private Category category; // Liên kết đến Category (nếu có)

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Product() {
    }
}
