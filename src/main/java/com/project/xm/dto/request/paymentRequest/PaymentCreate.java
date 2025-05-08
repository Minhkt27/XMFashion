package com.project.xm.dto.request.paymentRequest;

import com.project.xm.model.Order;
import com.project.xm.model.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentCreate {
    private Long orderId;

    private String paymentMethod; // Ví dụ: "Cash", "Credit Card", "Momo", v.v.

    private PaymentStatus paymentStatus;

    private String transactionId; // Mã giao dịch, có thể null nếu là thanh toán tiền mặt

}
