package com.project.xm.controller;

import com.project.xm.dto.request.paymentRequest.PaymentCreate;
import com.project.xm.model.Payment;
import com.project.xm.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<Payment> getAll(){
        return paymentService.getAll();
    }

    @GetMapping("/{id}")
    public Payment getById(@PathVariable Long id){
        return paymentService.getById(id);
    }
    @GetMapping("/transaction/{transactionId}")
    public Payment getByTransactionId(@PathVariable String transactionId){
        return paymentService.getByTransactionId(transactionId);
    }
    @PostMapping
    public Payment createPayment(@RequestBody PaymentCreate request){
        return paymentService.createRequest(request);
    }

    @DeleteMapping("/{id}")
    public String deletePayment(@PathVariable Long id){
        paymentService.deleteRequest(id);

        return "Payment has been deleted";
    }

}
