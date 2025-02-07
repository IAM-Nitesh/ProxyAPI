package org.springboot.proxyapi.controller;

import org.springboot.proxyapi.exceptions.ProductNotFoundException;
import org.springboot.proxyapi.model.Product;
import org.springboot.proxyapi.service.ProductService;
import org.springboot.proxyapi.service.SelfProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    ProductController(@Qualifier("SelfProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {

        Product product = productService.getProductById(id);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping()
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> removeProduct(@PathVariable("id") long id) throws ProductNotFoundException{
        Product product = productService.removeProductById(id);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
}
