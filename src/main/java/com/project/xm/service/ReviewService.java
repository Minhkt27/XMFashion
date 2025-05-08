package com.project.xm.service;

import com.project.xm.dto.request.reviewRequest.ReviewCreate;
import com.project.xm.model.Product;
import com.project.xm.model.Review;
import com.project.xm.model.User;
import com.project.xm.repository.ProductRepository;
import com.project.xm.repository.ReviewRepository;
import com.project.xm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Review> getAll(){
        return reviewRepository.findAll();
    }
    public Review getById(Long id){
        return reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
    }
    public List<Review> getByRating(int rating){
        if(reviewRepository.existsByRating(rating))
            throw new RuntimeException("Review not found");
        return reviewRepository.findByRating(rating);
    }
    public List<Review> getByProductId(Long id){
        if(reviewRepository.existsByProduct_ProductID(id))
            throw new RuntimeException("Review not found");
        return reviewRepository.findByProduct_ProductID(id);
    }

    public Review createRequest(ReviewCreate request){
        if(reviewRepository.existsByUser_UserID(request.getUserId()) || reviewRepository.existsByProduct_ProductID(request.getProductId()))
            throw new RuntimeException("Review existed");

        Product product= productRepository.findById(request.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
        User user= userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Review review=new Review();

        review.setUser(user);
        review.setProduct(product);
        review.setRating(request.getRating());
        review.setComment(request.getComment());

        return reviewRepository.save(review);
    }
    public void deleteRequest(Long id){
        if (reviewRepository.existsById(id))
            throw  new RuntimeException("Review not found");
        reviewRepository.deleteById(id);
    }
}
