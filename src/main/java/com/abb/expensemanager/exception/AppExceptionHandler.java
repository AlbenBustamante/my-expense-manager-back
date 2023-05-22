package com.abb.expensemanager.exception;

import com.abb.expensemanager.util.constants.DateFormats;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The main application exception handler for customizing responses.
 */
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String TIMESTAMP = "timestamp";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        final Map<String, String> body = new LinkedHashMap<>();

        body.put(TIMESTAMP, timestamp());

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(field -> body.put(field.getField(), field.getDefaultMessage()));

        return ResponseEntity.badRequest().body(body);
    }

    /**
     * Customize the handling of {@link AppException}.
     *
     * @param ex the exception to handle.
     * @return a {@link ProblemDetail} for the response to use.
     */
    @ExceptionHandler(AppException.class)
    public ProblemDetail handleApp(AppException ex) {
        final var problemDetail = ProblemDetail.forStatusAndDetail(ex.getStatus(), ex.getMessage());
        problemDetail.setProperty(TIMESTAMP, timestamp());

        return problemDetail;
    }

    /**
     * Customize the handling of {@link RuntimeException} and {@link Exception}.
     *
     * @param ex the exception to handle.
     * @return a {@link ProblemDetail} for the response to use.
     */
    @ExceptionHandler({RuntimeException.class, Exception.class})
    public ProblemDetail handle(Exception ex) {
        final var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setProperty(TIMESTAMP, timestamp());

        return problemDetail;
    }

    /**
     * Catch the current datetime.
     *
     * @return the datetime as a string.
     */
    private String timestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateFormats.DATE_TIME_FORMAT));
    }

}
