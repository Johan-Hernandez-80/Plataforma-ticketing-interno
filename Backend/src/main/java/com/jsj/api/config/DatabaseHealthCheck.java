/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Juan José Molano Franco
 */
@Component
public class DatabaseHealthCheck {

    private final DataSource dataSource;

    @Autowired
    public DatabaseHealthCheck(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean testConnection() {
        try (Connection connection = dataSource.getConnection()) {
            return connection.isValid(2); // 2-second timeout
        } catch (SQLException e) {
            // Log the error for debugging
            System.err.println("Database connection failed: " + e.getMessage());
            return false;
        }
    }
}
