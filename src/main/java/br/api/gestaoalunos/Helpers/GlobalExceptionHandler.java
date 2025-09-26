package br.api.gestaoalunos.Helpers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleBetterValidationExceptions(MethodArgumentNotValidException exception) {
    
    List<Map<String, String>> errorDetails = exception.getFieldErrors()
            .stream()
            .map(fieldError -> Map.of(
                    "campo", fieldError.getField(),
                    "erro", fieldError.getDefaultMessage()
            ))
            .collect(Collectors.toList());

    Map<String, Object> responseBody = Map.of(
            "status", 400,
            "erro", "Erro de Validação",
            "detalhes", errorDetails
    );

    return ResponseEntity.badRequest().body(responseBody);
    }
}
