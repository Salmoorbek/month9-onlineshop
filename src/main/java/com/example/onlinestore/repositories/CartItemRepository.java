package com.example.onlinestore.repositories;

import com.example.onlinestore.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query(nativeQuery = true, value = "select * from cart_items where cart_id = :cartId")
    List<CartItem> selectCartItemsByCartId(Long cartId);

}
