package com.sachet.departmentservice.error_handler;

import com.sachet.departmentservice.custom_error.DepartmentNotFoundException;
import com.sachet.departmentservice.error_to_return.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        ApiError apiError = new ApiError(400, "Validation Error!", request.getContextPath());
        BindingResult result = ex.getBindingResult();
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError error: result.getFieldErrors()){
            validationErrors.put(error.getField(), error.getDefaultMessage());
        }
        apiError.setValidationErrors(validationErrors);
        System.out.println(apiError);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ApiError> handleDepartmentNotFoundException(
            DepartmentNotFoundException ex, WebRequest request
    ){
        return new ResponseEntity<>(
                new ApiError(
                        404,
                        ex.getMessage(),
                        request.getContextPath()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolationException(
            ConstraintViolationException ex
    ){
        return new ResponseEntity<>(
                new ApiError(
                        400,
                        ex.getMessage()
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}











