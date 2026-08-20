package hr.tvz.laploggerapp.exception;

import hr.tvz.laploggerapp.dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(
            MethodArgumentNotValidException e, HttpServletRequest request) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));

        LOGGER.warn("Validation failed at {}: {}", request.getRequestURI(), message);

        return buildErrorResponse(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleUnexpectedException(Exception e, HttpServletRequest request) {
        String referenceId = UUID.randomUUID().toString();
        LOGGER.error("Unhandled exception [referenceId={}] at {}", referenceId, request.getRequestURI(), e);

        String message = "An unexpected error occurred. Reference ID: " + referenceId;

        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFound(
            ResourceNotFoundException e, HttpServletRequest request) {
        LOGGER.warn("Resource not found at {}: {}", request.getRequestURI(), e.getMessage());

        return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> handleMessageNotReadable(
            HttpMessageNotReadableException e, HttpServletRequest request) {
        LOGGER.warn("Malformed request body at {}: {}", request.getRequestURI(), e.getMessage());

        return buildErrorResponse(HttpStatus.BAD_REQUEST,
                "Malformed request body. Please check field values and types.");
    }

    private ResponseEntity<ErrorResponseDto> buildErrorResponse(HttpStatus status, String message) {
        ErrorResponseDto error = new ErrorResponseDto(
                status.value(), message, LocalDateTime.now(ZoneId.of("Europe/Zagreb")));

        return ResponseEntity.status(status).body(error);
    }
}
