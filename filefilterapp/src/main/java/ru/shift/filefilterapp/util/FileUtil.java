package ru.shift.filefilterapp.util;

import org.springframework.stereotype.Component;
import ru.shift.filefilterapp.exception.DirectoryCreatingFailureException;
import ru.shift.filefilterapp.exception.FileNotFoundException;
import ru.shift.filefilterapp.exception.FileNotReadableException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Component
public class FileUtil {

  public void isValid(Path path) {
    if (!Files.exists(path)) {
      throw new FileNotFoundException(path);
    }
    if (!Files.isReadable(path)) {
      throw new FileNotReadableException(path);
    }
  }

  public void createParentDirectory(Path path) {
    Optional.ofNullable(path.getParent())
        .ifPresent(parent -> {
          try {
            Files.createDirectories(parent);
          } catch (IOException e) {
            throw new DirectoryCreatingFailureException(parent);
          }
        });
  }
}
