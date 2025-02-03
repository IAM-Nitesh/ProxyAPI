package org.springboot.proxyapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@Entity
public class Product extends BaseModel{

    private String description;
    @ManyToOne
    private Category category;
    private Double price;
}