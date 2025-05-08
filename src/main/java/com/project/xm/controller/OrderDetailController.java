package com.project.xm.controller;

import com.project.xm.dto.request.orderDetailRequest.OrderDetailCreate;
import com.project.xm.dto.request.orderDetailRequest.OrderDetailUpdate;
import com.project.xm.model.OrderDetail;
import com.project.xm.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/details")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/{id}")
    public OrderDetail getById(@PathVariable()Long id){
        return orderDetailService.getById(id);
    }
    @GetMapping
    public List<OrderDetail> getAll(){
        return orderDetailService.getAll();
    }
    @GetMapping("/order/{orderId}")
    public List<OrderDetail> getByUserId(@PathVariable("orderId") Long orderId){
        return orderDetailService.getByOrderId(orderId);
    }
    @PostMapping
    public OrderDetail createOrder(@RequestBody OrderDetailCreate request ){
        return orderDetailService.createRequest(request);
    }
    @PutMapping("/{id}")
    public OrderDetail updateOrder(@PathVariable("id") Long id, @RequestBody OrderDetailUpdate request){
        return orderDetailService.updateRequest(id,request);
    }
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable("id") Long id){
        orderDetailService.deleteRequest(id);
        return "Order has been deleted";
    }
}
