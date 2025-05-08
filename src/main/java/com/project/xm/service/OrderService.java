package com.project.xm.service;

import com.project.xm.dto.request.orderDetailRequest.OrderDetailCreate;
import com.project.xm.dto.request.orderRequest.OrderCreate;
import com.project.xm.dto.request.orderRequest.OrderUpdate;
import com.project.xm.model.Order;

import com.project.xm.model.OrderDetail;
import com.project.xm.model.User;
import com.project.xm.repository.OrderRepository;
import com.project.xm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderDetailService orderDetailService;


    public List<Order> getAll(){
        return orderRepository.findAll();
    }
    public List<Order> getByUserId(Long id){
        return orderRepository.findByUser_UserID(id);
    }
    public Order getById(Long id){
        return orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Order not found"));
    }

    public Order createRequest(OrderCreate request){
        if (orderRepository.existsById(request.getId()))
            throw new RuntimeException("Order existed");
        User user= userRepository.findById(request.getUserId())
                .orElseThrow(()->new RuntimeException("User not found."));
        Order order=new Order();

        order.setUser(user);
        order.setTotalPrice(request.getTotalPrice());
        order.setStatus(request.getStatus());
        // Tạo OrderDetails từ OrderCreate
        List<OrderDetail> details=new ArrayList<>();
        for (OrderDetailCreate detailCreate : request.getOrderDetailCreates()) {
            details.add(orderDetailService.createRequest(detailCreate)); // Gọi OrderDetailService để thêm OrderDetail
        }
        order.setOrderDetail(details);
        return orderRepository.save(order);
    }
    public Order updateRequest(Long id,OrderUpdate request){
        Order order=getById(id);

        order.setTotalPrice(BigDecimal.valueOf(request.getTotalPrice()));
        order.setStatus(request.getStatus());

        return orderRepository.save(order);
    }
    public void deleteRequest(Long id){
        if(!orderRepository.existsById(id))
            throw new RuntimeException("Order not found");

        orderRepository.deleteById(id);
    }
}
