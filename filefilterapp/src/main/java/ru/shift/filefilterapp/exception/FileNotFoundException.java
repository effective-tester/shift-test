package ru.shift.filefilterapp.exception;

import java.nio.file.Path;

public class FileNotFoundException extends FileFilterRuntimeException {

  public FileNotFoundException(Path path) {
    super(String.format("Не удалось найти файл: %s", path));
  }
}
