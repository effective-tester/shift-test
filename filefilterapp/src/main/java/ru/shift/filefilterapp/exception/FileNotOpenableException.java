package ru.shift.filefilterapp.exception;

import java.nio.file.Path;

public class FileNotOpenableException extends FileFilterRuntimeException {

  public FileNotOpenableException(Path path) {
    super(String.format("Не получилось записать в файл: %s", path));
  }
}
