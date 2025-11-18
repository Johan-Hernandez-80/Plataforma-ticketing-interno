package com.jsj.api;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BackendApplication {

    static {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(e -> System.setProperty(e.getKey(), e.getValue()));
        // Ensure Spring profile is set early
        if (System.getProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME) == null) {
            String profile = System.getProperty("SPRING_PROFILES_ACTIVE", "dev");
            System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, profile);
        }
    }

    public static void main(String[] args) {
//        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
//        dotenv.entries().forEach(e -> System.out.println(String.format("Key: %s, value: %s", e.getKey(), e.getValue())));

        System.out.println(new BCryptPasswordEncoder().encode("admin123"));
        System.out.println(new BCryptPasswordEncoder().encode("agente123"));
        System.out.println(new BCryptPasswordEncoder().encode("usuario123"));
        SpringApplication.run(BackendApplication.class, args);
    }
}
