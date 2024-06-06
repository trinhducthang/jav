package com.java1504.ManagerUsers.exception;


import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(Exception e, WebRequest request) {
        System.out.println("============handleValidationException=============");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());

        String message = e.getMessage();

//        int start = message.lastIndexOf("[");
//        int end = message.lastIndexOf("]");
//        message = message.substring(start + 1, end -1);
        message = Objects.requireNonNull(((MethodArgumentNotValidException) e).getFieldError()).getDefaultMessage();
        errorResponse.setError("Payload Invalid");

        errorResponse.setMessage(message);

        return errorResponse;
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse halderNotFoundExeption(Exception e, WebRequest request){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(),e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse halderEntityNotFoundExeption(EntityNotFoundException e, WebRequest request){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(),e.getMessage());
    }

}
