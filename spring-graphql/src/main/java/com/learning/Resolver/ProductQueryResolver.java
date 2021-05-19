package com.learning.Resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.learning.Entity.Product;
import com.learning.Repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductQueryResolver implements GraphQLQueryResolver {

    private final ProductRepository productRepository;

    public ProductQueryResolver(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductByName(String name) {
        return productRepository.getByNameContainsOrderByIdDesc(name);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
}
