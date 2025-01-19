package ru.shift.filefilterapp.exception;

import java.nio.file.Path;

public class FileNotReadableException extends FileFilterRuntimeException {

  public FileNotReadableException(Path path) {
    super(String.format("Не получилось прочитать файл: %s", path));
  }
}
