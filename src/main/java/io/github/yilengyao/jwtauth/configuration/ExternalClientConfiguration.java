package io.github.yilengyao.jwtauth.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ExternalClientConfiguration {

    @Bean
    public MongoClient mongoClient(@Value("${MONGO_DATABASE_URI:}") String mongoDatabaseUri) {
        if (mongoDatabaseUri.isEmpty()) {
            log.warn("MONGO_DATABASE_URI not set in the environment variables");
            throw new IllegalArgumentException("MONGO_DATABASE_URI not set in the environment variables");
        }

        // ServerApi serverApi = ServerApi.builder()
        //         .version(ServerApiVersion.V1)
        //         .build();
        //
        // MongoClientSettings settings = MongoClientSettings.builder()
        //         .applyConnectionString(new ConnectionString(mongoDatabaseUri))
        //         .serverApi(serverApi)
        //         .build();
        //
        return MongoClients.create(mongoDatabaseUri);
    }
}
