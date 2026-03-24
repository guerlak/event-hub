package br.com.guerlak.eventhub.infra.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // Indica que esta classe cuidará de erros globalmente
public class CustomExceptionHandler {

    // Captura especificamente a RuntimeException que lançamos no Service
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException exception) {
        // Se a mensagem for "Evento não encontrado", retornamos 404
        if (exception.getMessage().contains("não encontrado")) {
            return ResponseEntity.notFound().build();
        }

        // Para outros erros de lógica, retornamos 400 (Bad Request)
        return ResponseEntity.badRequest().body(new ExceptionDTO(exception.getMessage()));
    }

    // Record interno para formatar a mensagem de erro no JSON
    public record ExceptionDTO(String message) {}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationErrors(MethodArgumentNotValidException exception) {
        // Pega todos os erros de campos e transforma em uma lista simples
        var errors = exception.getFieldErrors().stream()
                .map(error -> new ValidationErrorDTO(error.getField(), error.getDefaultMessage()))
                .toList();

        return ResponseEntity.badRequest().body(errors);
    }

    public record ValidationErrorDTO(String field, String message) {}
}