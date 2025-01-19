package ru.shift.filefilterapp.filefilter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.shift.filefilterapp.command.Claim;
import ru.shift.filefilterapp.exception.FileNotReadableException;
import ru.shift.filefilterapp.util.FileUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class FilesFilterReader {

  private final LineProcessor lineProcessor;

  private final FileUtil fileUtil;

  private final Claim claim;

  public void readFiles() {
    claim.getInputFiles().stream()
        .map(Paths::get)
        .peek(fileUtil::isValid)
        .forEach(this::readFile);
  }

  private void readFile(Path path) {
    try (BufferedReader reader = Files.newBufferedReader(path)) {
      reader.lines()
          .forEach(lineProcessor::process);
    } catch (IOException e) {
      throw new FileNotReadableException(path);
    }
  }
}

