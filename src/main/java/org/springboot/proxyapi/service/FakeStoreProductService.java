package org.springboot.proxyapi.service;

import org.springboot.proxyapi.dto.FakeStoreDto;
import org.springboot.proxyapi.exceptions.ProductNotFoundException;
import org.springboot.proxyapi.model.Category;
import org.springboot.proxyapi.model.Product;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import javax.management.InstanceNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;

    public FakeStoreProductService( RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
       FakeStoreDto fakeStoreDto = restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreDto.class);

       if (fakeStoreDto == null)
       {
           throw new ProductNotFoundException(100L,"Product not found with id : " + id);
       }

       return convertFakeStoreDtoToProduct(fakeStoreDto);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreDto[] fakeStoreDto = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreDto[].class);
        List<Product> products = new ArrayList<>();

        if ( fakeStoreDto == null){
            return null;
        }
        else{
            for (FakeStoreDto storeDto : fakeStoreDto) {
                products.add(convertFakeStoreDtoToProduct(storeDto));
            }
        }
        return products;
    }

    @Override
    public Product addProduct() {
        FakeStoreDto fakeStoreDto = new FakeStoreDto();
        fakeStoreDto.setTitle("test product");
        fakeStoreDto.setPrice(13.5);
        fakeStoreDto.setDescription("lorem ipsum set");
        fakeStoreDto.setCategory("electronic");
        FakeStoreDto fSDto = restTemplate.postForObject("https://fakestoreapi.com/products",fakeStoreDto,FakeStoreDto.class);

        return convertFakeStoreDtoToProduct(fSDto);
    }

    @Override
    public Product updateProduct(Long id, Product product) {

        FakeStoreDto fakeStoreDto  = new FakeStoreDto();
        fakeStoreDto.setId(product.getId());
        fakeStoreDto.setPrice(product.getPrice());
        fakeStoreDto.setDescription(product.getDescription());
        fakeStoreDto.setCategory(product.getCategory().getType());
        fakeStoreDto.setTitle(product.getTitle());

        ResponseExtractor<ResponseEntity<FakeStoreDto>> responseExtractor =
                restTemplate.
                        responseEntityExtractor(FakeStoreDto.class);

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreDto, FakeStoreDto.class);

        FakeStoreDto fakeStoreDto1 =
                restTemplate.
                    execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor)
                        .getBody();

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

