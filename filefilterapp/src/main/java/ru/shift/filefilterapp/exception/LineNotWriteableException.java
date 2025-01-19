package ru.shift.filefilterapp.exception;

import java.nio.file.Path;

public class LineNotWriteableException extends FileFilterRuntimeException {

  public LineNotWriteableException(String line, Path path) {
    super(String.format("Не получилось записать строку %s в файл: %s", line, path));
  }
}
