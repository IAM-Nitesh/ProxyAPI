package org.springboot.proxyapi.controller;

import org.springboot.proxyapi.dto.FakeStoreDto;
import org.springboot.proxyapi.dto.ProductNotFoundExceptionDto;
import org.springboot.proxyapi.exceptions.ProductNotFoundException;
import org.springboot.proxyapi.model.Product;
import org.springboot.proxyapi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;
import java.util.List;

@RestController()
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {

        Product product = productService.getProductById(id);

        return new ResponseEntity<Product>(product,HttpStatus.OK);
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping()
    public Product addProduct() {
        return productService.addProduct();
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }



}
