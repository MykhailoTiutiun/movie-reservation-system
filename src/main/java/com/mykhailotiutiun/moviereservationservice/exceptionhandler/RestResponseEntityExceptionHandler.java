package com.mykhailotiutiun.moviereservationservice.exceptionhandler;

import com.mykhailotiutiun.moviereservationservice.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException e, WebRequest request){
        return handleExceptionInternal(e, "Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = AlreadyExistsException.class)
    protected ResponseEntity<Object> handleAlreadyExistsException(AlreadyExistsException e, WebRequest request){
        return handleExceptionInternal(e, "Already Exists", new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = ReservationException.class)
    protected ResponseEntity<Object> handleReservationException(ReservationException e, WebRequest request){
        return handleExceptionInternal(e, "Reservation Error", new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = PasswordMatchesException.class)
    protected ResponseEntity<Object> handlePasswordMatchesException(PasswordMatchesException e, WebRequest request){
        return handleExceptionInternal(e, "Passwords doesn't match", new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(value = UnverifiedEmailException.class)
    protected ResponseEntity<Object> handleUnverifiedEmailException(UnverifiedEmailException e, WebRequest request){
        return handleExceptionInternal(e, "User is unverified", new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(value = InvalidDeletionException.class)
    protected ResponseEntity<Object> handleInvalidDeletionException(InvalidDeletionException e, WebRequest request){
        return handleExceptionInternal(e, "Invalid deletion", new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
