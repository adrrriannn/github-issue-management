package com.adrrriannn.issue.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by adrian on 18/06/19.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({HttpClientErrorException.class})
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleBadParameter(HttpServletRequest req, HttpClientErrorException ex) {

        ErrorMessage errorMessage = ErrorMessage.builder().exception(ex.getClass())
                .status(ex.getStatusCode().value())
                .message(ex.getMessage())
                .url(req.getRequestURI())
                .timestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(errorMessage, ex.getStatusCode());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler ({Exception.class})
    @ResponseBody
    public ErrorMessage handleInternalServerError(HttpServletRequest req, Exception ex) {
        return ErrorMessage.builder().exception(ex.getClass())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .url(req.getRequestURI())
                .timestamp(System.currentTimeMillis())
                .build();
    }
}
