package com.project.xm.service;


import com.project.xm.model.Cart;
import com.project.xm.model.CartItem;
import com.project.xm.model.User;
import com.project.xm.repository.CartItemRepository;
import com.project.xm.repository.CartRepository;
import com.project.xm.repository.ProductRepository;
import com.project.xm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    // Tạo giỏ hàng mới cho người dùng
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    // Lấy giỏ hàng của người dùng
    public Cart getCartByUser(User user) {
        return cartRepository.findByUser(user);
    }

    // Xóa giỏ hàng của người dùng
    public void deleteCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cartRepository.delete(cart);
    }

    // Lấy tất cả các items trong giỏ hàng
    public List<CartItem> getCartItems(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        return cart.getItems();
    }
}
