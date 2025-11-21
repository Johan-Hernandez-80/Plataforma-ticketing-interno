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

        System.out.println(new BCryptPasswordEncoder().encode("Ana López"));
        System.out.println(new BCryptPasswordEncoder().encode("Carlos Ruiz"));
        System.out.println(new BCryptPasswordEncoder().encode("Laura Fernández"));
        System.out.println(new BCryptPasswordEncoder().encode("Pedro Sánchez"));
        System.out.println(new BCryptPasswordEncoder().encode("Sofía Torres"));
        System.out.println(new BCryptPasswordEncoder().encode("Diego Morales"));
        System.out.println(new BCryptPasswordEncoder().encode("Valeria Castro"));
        System.out.println(new BCryptPasswordEncoder().encode("Miguel Ángel Herrera"));
        System.out.println(new BCryptPasswordEncoder().encode("Camila Ortiz"));
        System.out.println(new BCryptPasswordEncoder().encode("Andrés Vargas"));
        SpringApplication.run(BackendApplication.class, args);
    }
}
