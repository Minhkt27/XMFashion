package com.project.xm.service;

import com.project.xm.dto.request.promotionRequest.PromoCreate;
import com.project.xm.dto.request.promotionRequest.PromoUpdate;
import com.project.xm.model.Promotion;
import com.project.xm.repository.PromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromoService {
    @Autowired
    private PromoRepository promoRepository;

    public List<Promotion> getAll(){
        return promoRepository.findAll();
    }
    public Promotion getById(Long id){
        return promoRepository.findById(id).orElseThrow(() -> new RuntimeException("Promotion not found"));
    }

    public Promotion getByCode(String code){
        if (!promoRepository.existsByCode(code))
            throw  new RuntimeException("Promotion not found");
        return promoRepository.findByCode(code);
    }

    public Promotion createRequest(PromoCreate request){
        if(promoRepository.existsByCode(request.getCode()))
            throw new RuntimeException("Code existed");

        Promotion promo=new Promotion();

        promo.setCode(request.getCode());
        promo.setDescription(request.getDescription());
        promo.setApplyFor(request.getApplyFor());
        promo.setDiscountValue(request.getDiscountValue());
        promo.setMinOrderValue(request.getMinOrderValue());
        promo.setMaxDiscount(request.getMaxDiscount());
        promo.setStartDate(request.getStartDate());
        promo.setEndDate(request.getEndDate());


        return promoRepository.save(promo);
    }

    public Promotion updateRequest(Long id, PromoUpdate request){
        if(!promoRepository.existsById(id))
            throw new RuntimeException("Promotion not found");
        Promotion promo=getById(id);
        promo.setDescription(request.getDescription());
        promo.setApplyFor(request.getApplyFor());
        promo.setDiscountValue(request.getDiscountValue());
        promo.setMinOrderValue(request.getMinOrderValue());
        promo.setMaxDiscount(request.getMaxDiscount());
        promo.setStartDate(request.getStartDate());
        promo.setEndDate(request.getEndDate());


        return promoRepository.save(promo);
    }

    public void deleteRequest(Long id){
        if(!promoRepository.existsById(id))
            throw new RuntimeException("Promotion not found");
        promoRepository.deleteById(id);
    }
}
