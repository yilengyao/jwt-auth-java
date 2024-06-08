package io.github.yilengyao.jwtauth.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ExternalClientConfiguration {

    private static final String ERROR_MESSAGE = "MONGO_DATABASE_URI not set in the environment variables";

    @Bean
    public MongoClient mongoClient(@Value("${MONGO_DATABASE_URI:}") String mongoDatabaseUri) {
        if (mongoDatabaseUri == null || mongoDatabaseUri.isEmpty()) {
            log.warn(ERROR_MESSAGE);
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }

        return MongoClients.create(mongoDatabaseUri);
    }
}
