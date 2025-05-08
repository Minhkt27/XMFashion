package com.project.xm.service;

import com.project.xm.dto.request.paymentRequest.PaymentCreate;
import com.project.xm.model.Order;
import com.project.xm.model.Payment;
import com.project.xm.repository.OrderRepository;
import com.project.xm.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    private OrderRepository orderRepository;

    public List<Payment> getAll(){
        return paymentRepository.findAll();
    }
    public Payment getById(Long id){
        return paymentRepository.findById(id).orElseThrow(()-> new RuntimeException("Payment not found."));
    }
    public Payment getByTransactionId(String transactionId){
        if(!paymentRepository.existsByTransactionId(transactionId))
            throw new RuntimeException("Payment not found.");
        return paymentRepository.findByTransactionId(transactionId);
    }

    public Payment createRequest(PaymentCreate request){
        if( paymentRepository.existsByTransactionId(request.getTransactionId()))
            throw new RuntimeException("Payment existed.");

        Order order=orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found."));

        Payment payment=new Payment();

        payment.setOrder(order);
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setPaymentStatus(request.getPaymentStatus());
        payment.setTransactionId(generateTransactionId());
        return paymentRepository.save(payment);
    }

    public void deleteRequest(Long id){
        if (!paymentRepository.existsById(id))
            throw new RuntimeException("Payment not found.");

        paymentRepository.deleteById(id);
    }
    // Hàm tạo mã giao dịch ngẫu nhiên với định dạng tùy chỉnh
    private String generateTransactionId() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder transactionId = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(chars.length());
            transactionId.append(chars.charAt(index));
        }

        return transactionId.toString();
    }
}
