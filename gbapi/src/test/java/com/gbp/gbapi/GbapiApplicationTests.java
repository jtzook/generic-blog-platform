package com.gbp.gbapi;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(initializers = GbapiApplicationTests.Initializer.class)
class GbapiApplicationTests {

    @BeforeAll
    static void setup() {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("DATABASE_URL", dotenv.get("DATABASE_URL"));
        System.setProperty("DATABASE_USER", dotenv.get("DATABASE_USER"));
        System.setProperty("DATABASE_PASSWORD", dotenv.get("DATABASE_PASSWORD"));
        System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
    }

    @Test
    void contextLoads() {
        // This test is implicitly performed by the @SpringBootTest annotation.
        // It acts as a smoke test, validating that the application's basic configuration is correct.
        // If the application context fails to load, the test will fail.
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + System.getProperty("DATABASE_URL"),
                    "spring.datasource.username=" + System.getProperty("DATABASE_USER"),
                    "spring.datasource.password=" + System.getProperty("DATABASE_PASSWORD"),
                    "spring.jwtSecret=" + System.getProperty("JWT_SECRET")
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
