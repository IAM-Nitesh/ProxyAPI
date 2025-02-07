package org.springboot.proxyapi.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springboot.proxyapi.exceptions.ProductNotFoundException;
import org.springboot.proxyapi.model.Product;
import org.springboot.proxyapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ProductControllerTest {

    @Mock(name = "SelfProductService")
    ProductService productService;

    @InjectMocks
    @Autowired
    ProductController productController;


    @Test
    void getProductById() throws ProductNotFoundException {

        // arrange
        long productID = 1L;
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Test 1");

        // mock rule
        when(productService.getProductById(1L)).thenReturn(product);

        // act
        Product p = productController.getProductById(productID).getBody();

        // assert
        Assertions.assertEquals("Test",p.getTitle());
        //Assertions.assertThrows(ProductNotFoundException.class,()->p.getTitle());
    }

    @Test
    void getProductByIdThrowsProductNotFoundException() throws ProductNotFoundException {

        // Arrange
        long productId = 2L;

        when(productService.getProductById(2l)).
                thenThrow(ProductNotFoundException.class);

        // Act & Assert
        Assertions.assertThrows(ProductNotFoundException.class,
                ()->productController.getProductById(productId).getBody());

    }

}
