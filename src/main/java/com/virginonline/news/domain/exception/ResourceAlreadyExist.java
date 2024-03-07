package com.virginonline.news.domain.exception;

public class ResourceAlreadyExist extends RuntimeException {

  public ResourceAlreadyExist(String message) {
    super(message);
  }
}
