package com.learning.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String name;
    private Double price;
    private Double amount;
    private Date createdAt;
}
