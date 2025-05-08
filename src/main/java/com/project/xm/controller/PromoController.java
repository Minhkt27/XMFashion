package com.project.xm.controller;

import com.project.xm.dto.request.promotionRequest.PromoCreate;
import com.project.xm.dto.request.promotionRequest.PromoUpdate;
import com.project.xm.model.Promotion;
import com.project.xm.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promos")
public class PromoController {
    @Autowired
    private PromoService promoService;

    @GetMapping
    public List<Promotion> getAll(){
        return promoService.getAll();
    }

    @GetMapping("/{code}")
    public Promotion getByCode(@PathVariable String code){
        return promoService.getByCode(code);
    }

    @PostMapping
    public Promotion promoCreate(@RequestBody PromoCreate request){
        return promoService.createRequest(request);
    }

    @PutMapping("/{id}")
    public Promotion promoUpdate(@PathVariable Long id, @RequestBody PromoUpdate request){
        return promoService.updateRequest(id,request);
    }

    @DeleteMapping("/{id}")
    public String promoDelete(@PathVariable Long id){
        promoService.deleteRequest(id);
        return "Promotion has been deleted";
    }
}
