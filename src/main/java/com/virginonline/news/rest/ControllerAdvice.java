package com.virginonline.news.rest;

import com.virginonline.news.domain.exception.ExceptionBody;
import com.virginonline.news.domain.exception.ResourceAlreadyExist;
import com.virginonline.news.domain.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ExceptionBody handleResourceNotFound(ResourceNotFoundException e) {
    return new ExceptionBody(e.getMessage());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ExceptionBody handleException(
      final Exception e
  ) {
    logger.error(e.getMessage());
    return new ExceptionBody("Internal error.");
  }

  @ExceptionHandler(ResourceAlreadyExist.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ExceptionBody handleResourceAlreadyExist(ResourceAlreadyExist e) {
    return new ExceptionBody(e.getMessage());
  }
}
