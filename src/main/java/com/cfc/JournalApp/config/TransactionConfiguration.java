package com.cfc.JournalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TransactionConfiguration {
    @Bean
    public PlatformTransactionManager validateTransaction(MongoDatabaseFactory dbFactory){
        return new MongoTransactionManager(dbFactory);
    }
}
