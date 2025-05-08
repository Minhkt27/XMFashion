package com.project.xm.controller;

import com.project.xm.dto.request.orderRequest.OrderCreate;
import com.project.xm.dto.request.orderRequest.OrderUpdate;
import com.project.xm.model.Order;
import com.project.xm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public Order getById(@PathVariable()Long id){
        return orderService.getById(id);
    }
    @GetMapping
    public List<Order> getAll(){
        return orderService.getAll();
    }
    @GetMapping("/{userId}")
    public List<Order> getByUserId(@PathVariable("userId") Long userId){
        return orderService.getByUserId(userId);
    }
    @PostMapping
    public Order createOrder(@RequestBody OrderCreate request){
        return orderService.createRequest(request);
    }
    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable("id") Long id, @RequestBody OrderUpdate request){
        return orderService.updateRequest(id,request);
    }
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable("id") Long id){
        orderService.deleteRequest(id);
        return "Order has been deleted";
    }
}
