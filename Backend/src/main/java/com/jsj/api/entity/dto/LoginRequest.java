/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @author Juan José Molano Franco
 */
@Data
@Schema(
    description = "Request payload for authentication. Contains the user's email (personal or corporate) and password. Both fields are required for login."
)
public class LoginRequest {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
