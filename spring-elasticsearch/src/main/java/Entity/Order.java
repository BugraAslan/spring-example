package Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.Date;

@Document(indexName = "orders")
@Getter
@Setter
public class Order {

    @Id
    private int id;

    @Field(name = "products", type = FieldType.Nested)
    private ArrayList<Product> products;

    @Field(name = "totalPrice", type = FieldType.Double)
    private double totalPrice;

    @Field(name = "totalAmount", type = FieldType.Double)
    private double totalAmount;

    @Field(name = "createdAt", type = FieldType.Date)
    private Date createdAt;
}
