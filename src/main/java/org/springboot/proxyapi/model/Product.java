package org.springboot.proxyapi.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class Product {

    private Long id;
    private String title;
    private String description;
    private Category category;
    private Double price;
}