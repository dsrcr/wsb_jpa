package com.jpacourse.rest.exception.advise;

import com.jpacourse.rest.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Global exception handler for handling {@link EntityNotFoundException}.
 * This class is annotated with {@link ControllerAdvice} and provides centralized exception
 * handling for all controllers in the application.
 * <p>
 * When an {@link EntityNotFoundException} is thrown, this handler intercepts it, sends a response
 * with an appropriate HTTP status code (404 NOT FOUND), and returns the exception message as the response body.
 */
@ControllerAdvice
public class EntityNotFoundAdvise {

    /**
     * Handles {@link EntityNotFoundException} exceptions.
     * When this exception is thrown by any controller, this method intercepts it,
     * sets the HTTP response status to 404 (NOT FOUND), and returns the exception message in the response body.
     *
     * @param ex the {@link EntityNotFoundException} that was thrown
     * @return a string containing the exception message to be returned in the response body
     */
    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(EntityNotFoundException ex) {
        return ex.getMessage();
    }

}
