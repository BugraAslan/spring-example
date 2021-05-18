package com.learning.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostProductRequest {

    private String name;
    private double amount;
    private double price;
}
