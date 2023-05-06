package com.example.onlinestore.controller;

import com.example.onlinestore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

//    @GetMapping("/products")
//    public List<ProductDto> getAllProducts(){
//        return productService.getAllProducts().stream()
//                .map(ProductMapper::fromPerson)
//                .collect(Collectors.toList());
//    }
//    @GetMapping("/{name}")
//    public ResponseEntity<ProductDto> getProductsByName(@PathVariable String name){
//        return new ResponseEntity<>(productService.getProductByName(name), HttpStatus.OK);
//    }
}
