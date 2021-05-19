package com.learning.Aggregator;

import com.learning.DTO.ProductDTO;
import com.learning.Entity.Product;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ProductAggregator {

    public Product prepareEntityByDTO(ProductDTO productDTO) {
        Product product = new Product();
        product.setAmount(productDTO.getAmount());
        product.setPrice(productDTO.getPrice());
        product.setName(productDTO.getName());
        product.setCreatedAt(new Date());

        return product;
    }
}
