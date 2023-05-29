package com.example.onlinestore.controller;

import com.example.onlinestore.dto.CartDto;
import com.example.onlinestore.dto.CartItemDto;
import com.example.onlinestore.dto.ProductDto;
import com.example.onlinestore.dto.UserDto;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.service.CartItemService;
import com.example.onlinestore.service.CartService;

import com.example.onlinestore.service.ProductService;
import com.example.onlinestore.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final ProductService productService;

    public CartController(CartService cartService, CartItemService cartItemService, ProductService productService) {
        this.cartService = cartService;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    @GetMapping("/carts")
    public String getCartPage(Model model, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = null;
        if (authentication.isAuthenticated()) {
            userEmail = authentication.getName();
            model.addAttribute("userEmail", userEmail);
        }

        CartDto cart = (CartDto) session.getAttribute("cart");
        if (cart == null) {
            cart = cartService.getCartByUserEmail(userEmail);
            session.setAttribute("cart", cart);
        }

        List<CartItemDto> cartItems = cartItemService.getCartItemByCartId(cart.getId());

        List<ProductDto> products = new ArrayList<>();
        for (CartItemDto cartItem : cartItems) {
            ProductDto product = productService.getProductDtoById(cartItem.getProductId());
            products.add(product);
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("products", products);

        return "carts";
    }

    @PostMapping("/carts/carts/items/remove")
    public String removeItemFromCart(@RequestParam("cartItemId") Long cartItemId, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = null;
        if (authentication.isAuthenticated()) {
            userEmail = authentication.getName();
        }

        CartDto cart = (CartDto) session.getAttribute("cart");

        if (cart != null) {
            cartService.removeItemFromCart(userEmail, cartItemId);
            List<CartItemDto> cartItems = cartItemService.getCartItemByCartId(cart.getId());
            cart.setCartItems(cartItems);

            session.setAttribute("cart", cart);
        }

        return "redirect:/carts";
    }

    @PostMapping("/carts/carts/items/add")
    public String addItemToCart(@RequestParam("productId") Long productId, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = null;
        if (authentication.isAuthenticated()) {
            userEmail = authentication.getName();
        }

        CartDto cart = (CartDto) session.getAttribute("cart");

        if (cart == null) {
            cart = cartService.getCartByUserEmail(userEmail);
            session.setAttribute("cart", cart);
        }
        cartService.addItemToCart(cart.getId(), productId);

        return "redirect:/carts";
    }
    @PostMapping("/carts/carts/items/update")
    public String updateCartItemQuantity(@RequestBody Map<String, Object> requestMap,
                                         HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = null;
        if (authentication.isAuthenticated()) {
            userEmail = authentication.getName();
        }

        CartDto cart = (CartDto) session.getAttribute("cart");

        if (cart != null) {
            Long cartItemId = Long.parseLong(requestMap.get("cartItemId").toString());
            Integer quantity = Integer.parseInt(requestMap.get("quantity").toString());

            cartService.updateCartItemQuantity(userEmail, cartItemId, quantity);
            List<CartItemDto> cartItems = cartItemService.getCartItemByCartId(cart.getId());
            cart.setCartItems(cartItems);

            session.setAttribute("cart", cart);
        }

        return "redirect:/carts";
    }
}
