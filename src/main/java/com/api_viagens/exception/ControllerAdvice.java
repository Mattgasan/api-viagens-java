package com.api_viagens.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class ControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    // Exceção para quando uma entidade não for encontrada
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        logger.error("Entidade não encontrada: {}", ex.getMessage());
        return new ResponseEntity<>("Entidade não encontrada: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Exceção para validações que falham
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<String> handleValidationException(javax.validation.ConstraintViolationException ex) {
        StringBuilder errorMessage = new StringBuilder("Erro de validação: ");
        ex.getConstraintViolations().forEach(violation -> {
            errorMessage.append(violation.getPropertyPath())
                    .append(": ")
                    .append(violation.getMessage())
                    .append(" ");
        });
        logger.warn("Erro de validação: {}", errorMessage.toString());
        return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
    }

    // Exceção para argumentos inválidos
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.warn("Argumento inválido: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // Exceção personalizada para conflitos em viagens
    @ExceptionHandler(TravelConflictException.class)
    public ResponseEntity<String> handleTravelConflictException(TravelConflictException ex) {
        logger.warn("Conflito de viagem: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Exceção genérica
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        logger.error("Erro inesperado: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("Erro inesperado: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
