package io.github.yilengyao.jwtauth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

@Configuration
public class RepositoryConfig {
    public static final String COMPANY_DATABASE = "CompanyDB";

    @Autowired
    private MongoClient mongoClient;

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient, COMPANY_DATABASE);
    }
}
