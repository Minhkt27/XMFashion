package com.project.xm.service;

import com.project.xm.dto.request.orderDetailRequest.OrderDetailCreate;
import com.project.xm.dto.request.orderDetailRequest.OrderDetailUpdate;
import com.project.xm.model.Order;
import com.project.xm.model.OrderDetail;
import com.project.xm.model.Product;
import com.project.xm.repository.OrderDetailRepository;
import com.project.xm.repository.OrderRepository;
import com.project.xm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    public List<OrderDetail> getAll(){
        return orderDetailRepository.findAll();
    }
    public List<OrderDetail> getByOrderId(Long id){
        return orderDetailRepository.findByOrder_OrderID(id);
    }
    public OrderDetail getById(Long id){
        return orderDetailRepository.findById(id).orElseThrow(()-> new RuntimeException("OrderDetail not found"));
    }
    public OrderDetail createRequest(OrderDetailCreate request){
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Order order= orderRepository.findById(request.getOrderId()).orElseThrow(()->new RuntimeException("Order not found."));
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setQuantity(request.getQuantity());
        orderDetail.setPrice(request.getPrice());
        return orderDetailRepository.save(orderDetail);
    }
    public OrderDetail updateRequest(Long id, OrderDetailUpdate request){
        OrderDetail orderDetail=getById(id);
        orderDetail.setQuantity(request.getQuantity());

        return orderDetailRepository.save(orderDetail);
    }
    public void deleteRequest(Long id){
        if(!orderDetailRepository.existsById(id))
            throw new RuntimeException("Order Detail not found");

        orderDetailRepository.deleteById(id);
    }
}
