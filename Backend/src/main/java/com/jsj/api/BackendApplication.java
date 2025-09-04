package com.jsj.api;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
                Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
                dotenv.entries().forEach(entry -> {
                System.setProperty(entry.getKey(), entry.getValue());
                System.out.println("Loaded: " + entry.getKey() + "=" + entry.getValue()); // Debug temporal
            });

		SpringApplication.run(BackendApplication.class, args);
	}

}
