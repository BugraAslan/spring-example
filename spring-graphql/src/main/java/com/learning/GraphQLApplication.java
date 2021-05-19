package com.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * {@link com.coxautodev.graphql.tools.GraphQLMutationResolver}
 * {@link com.coxautodev.graphql.tools.GraphQLQueryResolver}
 */
@SpringBootApplication

// TODO <<< localhost:8082/graphiql >>>
public class GraphQLApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphQLApplication.class, args);
    }
}
