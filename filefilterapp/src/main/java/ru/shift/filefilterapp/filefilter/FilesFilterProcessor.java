package ru.shift.filefilterapp.filefilter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FilesFilterProcessor {

  private final FilesFilterReader filesFilterReader;

  private final FilesFilterWriter filesFilterWriter;

  public void process() {
    filesFilterReader.readFiles();
    filesFilterWriter.writeFilteredData();
  }
}
