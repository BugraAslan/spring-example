package com.learning.Resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.learning.Aggregator.ProductAggregator;
import com.learning.DTO.ProductDTO;
import com.learning.Entity.Product;
import com.learning.Repository.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductMutationResolver implements GraphQLMutationResolver {

    private final ProductRepository productRepository;
    private final ProductAggregator productAggregator;

    public ProductMutationResolver(ProductRepository productRepository, ProductAggregator productAggregator) {
        this.productRepository = productRepository;
        this.productAggregator = productAggregator;
    }

    public Product createProduct(ProductDTO productDTO) {
        Product product = productAggregator.prepareEntityByDTO(productDTO);
        productRepository.save(product);

        return product;
    }
}
