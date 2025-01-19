package ru.shift.filefilterapp.exception;

public class OptionIncorrectException extends FileFilterRuntimeException {

  public OptionIncorrectException(String arg) {
    super(String.format("Не удалось распознать опцию: %s", arg));
  }
}
