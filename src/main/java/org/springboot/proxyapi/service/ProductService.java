package org.springboot.proxyapi.service;

import org.springboot.proxyapi.model.Product;


public interface ProductService {

    Product getProductById(Long id);
}
