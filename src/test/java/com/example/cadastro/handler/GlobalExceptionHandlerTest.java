package com.example.cadastro.handler;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void deveTratarIllegalArgumentException() {
        ResponseEntity<?> response = handler.handleIllegalArgument(new IllegalArgumentException("CPF inválido"));
        assertEquals(400, response.getStatusCode().value());
        assertTrue(((Map<?, ?>) response.getBody()).get("erro").toString().contains("CPF inválido"));
    }

    @Test
    void deveTratarEntityNotFoundException() {
        ResponseEntity<?> response = handler.handleNotFound(new EntityNotFoundException("Pessoa não encontrada"));
        assertEquals(404, response.getStatusCode().value());
        assertTrue(((Map<?, ?>) response.getBody()).get("erro").toString().contains("Pessoa não encontrada"));
    }

    @Test
    void deveTratarExceptionGenerica() {
        ResponseEntity<?> response = handler.handleGeneralError(new RuntimeException("Erro inesperado"));
        assertEquals(500, response.getStatusCode().value());
        assertTrue(((Map<?, ?>) response.getBody()).get("erro").toString().contains("Erro interno no servidor"));
    }

    @Test
    void deveTratarMethodArgumentNotValidException() {
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("pessoa", "cpf", "CPF é obrigatório");

        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<?> response = handler.handleValidationErrors(ex);

        assertEquals(400, response.getStatusCode().value());
        Map<?, ?> body = (Map<?, ?>) response.getBody();
        assertEquals("CPF é obrigatório", body.get("cpf"));
    }
}
