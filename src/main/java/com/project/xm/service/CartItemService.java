package com.project.xm.service;

import com.project.xm.dto.request.cartItemRequest.CartItemCreate;
import com.project.xm.dto.request.cartItemRequest.CartItemUpdate;
import com.project.xm.model.Cart;
import com.project.xm.model.CartItem;
import com.project.xm.model.Product;
import com.project.xm.model.User;
import com.project.xm.repository.CartItemRepository;
import com.project.xm.repository.CartRepository;
import com.project.xm.repository.ProductRepository;
import com.project.xm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private  ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;

    public CartItem addToCart(CartItemCreate request) {
        Cart cart=cartRepository.findById(request.getCartID())
                .orElseThrow(()-> new RuntimeException("Cart not found"));
        Product product = productRepository.findById(request.getProductID())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (request.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        CartItem cartItem = cartItemRepository.findByCart_CartIDAndProduct_ProductID(cart.getCartID(), product.getProductID())
                .orElse(CartItem.builder()
                        .cart(cart)
                        .product(product)
                        .quantity(0)
                        .build());

        cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
        return cartItemRepository.save(cartItem);
    }

    // Cập nhật số lượng sản phẩm trong giỏ
    public CartItem updateCartItem(CartItemUpdate request) {
        CartItem cartItem = cartItemRepository.findByCart_CartIDAndProduct_ProductID(request.getCartID(), request.getProductID())
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        if (request.getQuantity() <= 0) {
            cartItemRepository.delete(cartItem); // Nếu số lượng <= 0, xóa mục giỏ
        } else {
            cartItem.setQuantity(request.getQuantity());
            cartItemRepository.save(cartItem); // Cập nhật số lượng sản phẩm
        }

        return cartItem;
    }

    // Xóa sản phẩm khỏi giỏ hàng
    public void removeCartItem(Long cartId, Long productId) {
        CartItem cartItem = cartItemRepository.findByCart_CartIDAndProduct_ProductID(cartId, productId)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        cartItemRepository.delete(cartItem);
    }
}
