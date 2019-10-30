package com.waes.assignment.diff.application.handler;

import com.waes.assignment.diff.application.exception.DiffNotFoundException;
import com.waes.assignment.diff.application.exception.InvalidDiffException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DiffNotFoundException.class})
    public ResponseEntity handleNotFoundExceptions(HttpServletRequest servletRequest, Exception exception) {
        return handleErrorResponse(servletRequest, exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidDiffException.class})
    public ResponseEntity handleUnprocessableEntity(HttpServletRequest servletRequest, Exception exception) {
        return handleErrorResponse(servletRequest, exception, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private ResponseEntity<ApiError> handleErrorResponse(HttpServletRequest request, Exception exception,
                                                         HttpStatus status) {
        ApiError apiError = new ApiError()
                .withCause(exception.getMessage())
                .withUri(request.getRequestURI())
                .withTimestamp(new Date().toString())
                .withStatus(status.value());

        return new ResponseEntity<>(apiError, status);
    }
}
