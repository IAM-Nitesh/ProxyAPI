package org.springboot.proxyapi.service;

import org.springboot.proxyapi.exceptions.ProductNotFoundException;
import org.springboot.proxyapi.model.Category;
import org.springboot.proxyapi.model.Product;
import org.springboot.proxyapi.repos.CategoryRepo;
import org.springboot.proxyapi.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SelfProductService")
public class SelfProductService implements ProductService{

    ProductRepo productRepo;
    private final CategoryRepo categoryRepo;


    SelfProductService(ProductRepo productRepo, CategoryRepo categoryRepo){
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Product addProduct(Product product) {

//        Category category = product.getCategory();
//
//        if(category.getId()==null){
//            Category savedCategory = categoryRepo.save(category);
//            product.setCategory(savedCategory);
//        }
//        else{
//            // we should check if category is valid or not
//        }

        return productRepo.save(product);
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return productRepo.findById(id).get();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }


    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product removeProductById(Long id) throws ProductNotFoundException {
        return null;
    }
}
