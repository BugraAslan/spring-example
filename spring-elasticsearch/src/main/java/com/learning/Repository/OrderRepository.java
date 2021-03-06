package com.learning.Repository;

import com.learning.Entity.Order;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends ElasticsearchRepository<Order, Integer> {
}
