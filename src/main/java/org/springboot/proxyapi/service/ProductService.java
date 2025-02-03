package org.springboot.proxyapi.service;

import org.springboot.proxyapi.exceptions.ProductNotFoundException;
import org.springboot.proxyapi.model.Product;

import javax.management.InstanceNotFoundException;
import java.util.List;

public interface ProductService {

    Product getProductById(Long id) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product addProduct();

    Product updateProduct(Long id, Product product);

    Product removeProductById(Long id) throws ProductNotFoundException;

}