package com.jsj.api;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
//            System.out.println("Loaded: " + entry.getKey() + "=" + entry.getValue()); // Debug temporal
        });
        
        System.out.println(new BCryptPasswordEncoder().encode("admin123"));
        System.out.println(new BCryptPasswordEncoder().encode("agente123"));
        System.out.println(new BCryptPasswordEncoder().encode("usuario123"));

        SpringApplication.run(BackendApplication.class, args);
    }

}
