/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SecurityException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Juan José Molano Franco
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // -------- DB errors --------
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrity(DataIntegrityViolationException ex) {
        return buildResponse(HttpStatus.CONFLICT, "Database constraint violation");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Object> handleDuplicateKey(DuplicateKeyException ex) {
        return buildResponse(HttpStatus.CONFLICT, "Duplicate key");
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResult(EmptyResultDataAccessException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "Entity not found");
    }

    // -------- Validation errors --------
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // -------- Auth / Security errors --------
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex) {
        return buildResponse(HttpStatus.FORBIDDEN, "Access denied");
    }

    // -------- JWT errors --------
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> handleExpiredJwt(ExpiredJwtException ex) {
        return buildResponse(HttpStatus.UNAUTHORIZED, "Token expired");
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Object> handleMalformedJwt(MalformedJwtException ex) {
        return buildResponse(HttpStatus.UNAUTHORIZED, "Malformed token");
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<Object> handleUnsupportedJwt(UnsupportedJwtException ex) {
        return buildResponse(HttpStatus.UNAUTHORIZED, "Unsupported token");
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<Object> handleInvalidSignature(SecurityException ex) {
        return buildResponse(HttpStatus.UNAUTHORIZED, "Invalid token signature");
    }

    // -------- Fallback --------
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneral(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error: " + ex.getMessage());
    }

    // -------- Helper --------
    private ResponseEntity<Object> buildResponse(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }
}
