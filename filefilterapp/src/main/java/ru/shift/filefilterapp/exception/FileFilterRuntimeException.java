package ru.shift.filefilterapp.exception;

import lombok.Getter;

@Getter
public class FileFilterRuntimeException extends RuntimeException {

  public FileFilterRuntimeException(String message) {
    super(message);
  }
}
