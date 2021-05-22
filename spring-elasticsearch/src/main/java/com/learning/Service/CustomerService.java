package com.learning.Service;

import com.learning.Entity.Order;
import com.learning.Entity.Product;
import com.learning.Repository.OrderRepository;
import com.learning.Repository.ProductRepository;
import com.learning.Request.PostProductRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CustomerService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public CustomerService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public void addProduct(PostProductRequest productRequest) {
        Product product = new Product();
        product.setAmount(productRequest.getAmount());
        product.setPrice(productRequest.getPrice());
        product.setName(productRequest.getName());
        product.setCreatedAt(new Date());
        productRepository.save(product);
    }

    public Iterable<Product> findAllProduct() {
        return productRepository.findAll();
    }

    public Order addOrder() {
        Iterable<Product> products = productRepository.findAll();
        double totalPrice = 0;
        double totalAmount = 0;
        ArrayList<Product> productList = new ArrayList<>();
        for (Product product: products) {
            productList.add(product);
            totalPrice += product.getPrice();
            totalAmount += product.getPrice();
        }

        Order order = new Order();
        order.setProducts(productList);
        order.setTotalAmount(totalAmount);
        order.setTotalPrice(totalPrice);
        order.setCreatedAt(new Date());
        orderRepository.save(order);

        return order;
    }
}
