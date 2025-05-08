package com.project.xm.repository;

import com.project.xm.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    boolean existsByTransactionId(String transactionId);
    Payment findByTransactionId(String transactionId);
}
