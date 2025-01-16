package org.springboot.proxyapi.service;

import org.springboot.proxyapi.dto.FakeStoreDto;
import org.springboot.proxyapi.model.Category;
import org.springboot.proxyapi.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;

    public FakeStoreProductService( RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {
       FakeStoreDto fakeStoreDto = restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreDto.class);
       return convertFakeStoreDtoToProduct(fakeStoreDto);
    }

    public Product convertFakeStoreDtoToProduct(FakeStoreDto fakeStoreDto) {
        if (fakeStoreDto == null) {
            return null;
        }

        Product product = new Product();
        product.setId(fakeStoreDto.getId());
        product.setTitle(fakeStoreDto.getTitle());
        product.setPrice(fakeStoreDto.getPrice());
        product.setDescription(fakeStoreDto.getDescription());

        Category category = new Category();
        category.setType(fakeStoreDto.getCategory());
        product.setCategory(category);

        return product;
    }
}
