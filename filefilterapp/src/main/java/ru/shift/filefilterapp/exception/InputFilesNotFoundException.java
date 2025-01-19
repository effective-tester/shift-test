package ru.shift.filefilterapp.exception;

public class InputFilesNotFoundException extends FileFilterRuntimeException {

  public InputFilesNotFoundException() {
    super(String.format("Не были выбраны входные файлы"));
  }
}
