package io.github.yilengyao.jwtauth.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@SpringBootTest
@ActiveProfiles("development")
@Disabled
public class MongoClientTests {

    private static final String DATABASE_NAME = "CompanyDB";
    private static final String EMPLOYEES_COLLECTION_NAME = "employees";
    private static final String USERS_COLLECTION_NAME = "users";

    @Autowired
    private MongoClient mongoClient;

    private MongoDatabase database;

    @BeforeEach
    public void setUp() {
        database = mongoClient.getDatabase(DATABASE_NAME);
    }

    @Test
    public void shouldConnectToDatabase() {
        assertThat(database).isNotNull();
    }

    @Test
    public void shouldHaveEmployeesCollection() {
        MongoCollection<Document> collection = database.getCollection(EMPLOYEES_COLLECTION_NAME);
        assertThat(collection).isNotNull();
    }

    @Test
    public void shouldHaveUsersCollection() {
        MongoCollection<Document> collection = database.getCollection(USERS_COLLECTION_NAME);
        assertThat(collection).isNotNull();
    }
}
