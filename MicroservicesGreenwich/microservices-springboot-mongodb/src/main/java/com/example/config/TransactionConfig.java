package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

@Configuration
public class TransactionConfig {

    @Bean
    MongoTransactionManager transactionManager(MongoDbFactory factory){
        return new MongoTransactionManager(factory);
    }

}