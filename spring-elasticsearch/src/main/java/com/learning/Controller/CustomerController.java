package com.learning.Controller;

import Entity.Order;
import Entity.Product;
import com.learning.Request.PostProductRequest;
import com.learning.Service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add/product")
    public void createProduct(@RequestBody ArrayList<PostProductRequest> productRequests) {
        for (PostProductRequest request: productRequests) {
            customerService.addProduct(request);
        }
    }

    @PostMapping("/add/order")
    public Order createOrder() {
        return customerService.addOrder();
    }

    @GetMapping("/product/list")
    public Iterable<Product> getProductList() {
        return customerService.findAllProduct();
    }
}
