package com.project.xm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Pattern(regexp = "^$|^\\d{10}$", message = "Phone must be empty or exactly 10 digits")
    @Column(name = "phone", nullable = true, length = 10)
    private String phone;

    @Email(message = "Invalid email format")
    @Column(name = "email", nullable = false, length = 45, unique = true)
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters")
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "address", length = 45)
    private String address;

//    @Min(value = 16, message = "Age must be at least 16")
//    @Max(value = 120, message = "Age must be less than or equal to 120")
    @Column(name = "age" ,nullable = true)
    private int age;

    @Column(name = "roleID")
    private int roleId;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public User() {
    }
}
