package com.project.xm.repository;

import com.project.xm.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoRepository extends JpaRepository<Promotion,Long> {
    boolean existsByCode(String code);
    Promotion findByCode(String code);
}
