package com.example.onlinestore.repositories;

import com.example.onlinestore.entity.Cart;
import com.example.onlinestore.entity.CartItem;
import com.example.onlinestore.entity.Product;
import com.example.onlinestore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query(nativeQuery = true, value = "select * from cart_items where cart_id = :cartId")
    List<CartItem> selectCartItemsByCartId(Long cartId);

    boolean existsByCartAndProduct(Cart shoppingCart, Product product);

    CartItem findByCartAndProduct(Cart shoppingCart, Product product);

    List<CartItem> getCartItemsByCartUser(User user);
}
