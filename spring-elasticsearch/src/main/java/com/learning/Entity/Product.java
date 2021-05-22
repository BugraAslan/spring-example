package com.learning.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "products")
@Getter
@Setter
public class Product {

    @Id
    private int id;

    @Field(name = "name", type = FieldType.Text)
    private String name;

    @Field(name = "price", type = FieldType.Double)
    private double price;

    @Field(name = "amount", type = FieldType.Double)
    private double amount;

    @Field(name = "createdAt", type = FieldType.Date)
    private Date createdAt;
}
