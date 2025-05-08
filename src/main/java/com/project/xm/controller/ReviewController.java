package com.project.xm.controller;

import com.project.xm.model.Review;
import com.project.xm.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<Review> getAll(){
        return reviewService.getAll();
    }

    @GetMapping("/{id}")
    public Review getById(@PathVariable Long id){
        return reviewService.getById(id);
    }

    @GetMapping("/product/{id}")
    public List<Review> getByProductId(@PathVariable Long id){
        return reviewService.getByProductId(id);
    }

    @GetMapping("/rating/{rating}")
    public List<Review> getByRating(@PathVariable int rating){
        return reviewService.getByRating(rating);
    }


}
