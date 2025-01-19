package ru.shift.filefilterapp.exception;

import java.nio.file.Path;

public class DirectoryCreatingFailureException extends FileFilterRuntimeException {

  public DirectoryCreatingFailureException(Path path) {
    super(String.format("Не удалось создать директорию: %s", path));
  }
}
