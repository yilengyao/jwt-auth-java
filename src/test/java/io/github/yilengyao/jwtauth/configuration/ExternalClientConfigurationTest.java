package io.github.yilengyao.jwtauth.configuration;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@SpringBootTest
public class ExternalClientConfigurationTest {

    @Autowired
    private ExternalClientConfiguration externalClientConfiguration;

    @MockBean
    private Environment environment;

    @Test
    public void testMongoClientThrowsExceptionWhenDatabaseUriIsEmpty() {
        when(environment.getProperty("MONGO_DATABASE_URI")).thenReturn("");
        assertThrows(IllegalArgumentException.class, () -> externalClientConfiguration.mongoClient(""));
    }
 
    @Test
    public void testMongoClientThrowsExceptionWhenDatabaseUriIsNull() {
        when(environment.getProperty("MONGO_DATABASE_URI")).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> externalClientConfiguration.mongoClient(null));
    }

    @Configuration
    static class TestConfig {
        @Bean
        public ExternalClientConfiguration externalClientConfiguration() {
            return new ExternalClientConfiguration();
        }
    }
}
