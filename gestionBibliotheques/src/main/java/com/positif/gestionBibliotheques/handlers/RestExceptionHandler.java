package com.positif.gestionBibliotheques.handlers;

import com.positif.gestionBibliotheques.Exceptions.EntityNotFoundException;
import com.positif.gestionBibliotheques.Exceptions.ErrorCodes;
import com.positif.gestionBibliotheques.Exceptions.InvalidEntityException;
import com.positif.gestionBibliotheques.Exceptions.InvalidOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;


@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorDto> handleException(EntityNotFoundException exception, WebRequest webRequest) {

    final HttpStatus notFound = HttpStatus.NOT_FOUND;
    final ErrorDto errorDto = ErrorDto.builder()
            .code(exception.getErrorCodes())
            .httpCode(notFound.value())
            .message(exception.getMessage())
            .build();

    return new ResponseEntity<>(errorDto, notFound);
  }

  @ExceptionHandler(InvalidOperationException.class)
  public ResponseEntity<ErrorDto> handleException(InvalidOperationException exception, WebRequest webRequest) {

    final HttpStatus notFound = HttpStatus.BAD_REQUEST;
    final ErrorDto errorDto = ErrorDto.builder()
            .code(exception.getErrorCode())
            .httpCode(notFound.value())
            .message(exception.getMessage())
            .build();

    return new ResponseEntity<>(errorDto, notFound);
  }

  @ExceptionHandler(InvalidEntityException.class)
  public ResponseEntity<ErrorDto> handleException(InvalidEntityException exception, WebRequest webRequest) {
    final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

    final ErrorDto errorDto = ErrorDto.builder()
            .code(exception.getErrorCodes())
            .httpCode(badRequest.value())
            .message(exception.getMessage())
            .errors(exception.getErrors())
            .build();

    return new ResponseEntity<>(errorDto, badRequest);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ErrorDto> handleException(BadCredentialsException exception, WebRequest webRequest) {
    final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

    final ErrorDto errorDto = ErrorDto.builder()
            .code(ErrorCodes.BAD_CREDENTIALS)
            .httpCode(badRequest.value())
            .message(exception.getMessage())
            .errors(Collections.singletonList("Username et / ou mot de passe incorrect"))
            .build();

    return new ResponseEntity<>(errorDto, badRequest);
  }

}
