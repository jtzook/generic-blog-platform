package com.gbp.gbapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@RestController
public class GbapiApplication {

	public static void main(String[] args) {
    Dotenv dotenv = Dotenv.load();
    System.setProperty("DATABASE_URL", dotenv.get("DATABASE_URL"));
    System.setProperty("DATABASE_USER", dotenv.get("DATABASE_USER"));
    System.setProperty("DATABASE_PASSWORD", dotenv.get("DATABASE_PASSWORD"));
    System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));

		SpringApplication.run(GbapiApplication.class, args);
	}

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
      return String.format("Hello %s?!", name);
    }

}
