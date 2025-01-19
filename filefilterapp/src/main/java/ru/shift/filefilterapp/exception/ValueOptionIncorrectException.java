package ru.shift.filefilterapp.exception;

public class ValueOptionIncorrectException extends FileFilterRuntimeException {

  public ValueOptionIncorrectException(String arg) {
    super(String.format("Не удалось найти параметры после опции: %s", arg));
  }
}
