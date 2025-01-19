package ru.shift.filefilterapp.filefilter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.shift.filefilterapp.command.Claim;
import ru.shift.filefilterapp.constant.LineType;
import ru.shift.filefilterapp.exception.FileNotOpenableException;
import ru.shift.filefilterapp.exception.LineNotWriteableException;
import ru.shift.filefilterapp.util.FileUtil;
import ru.shift.filefilterapp.util.LineUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FilesFilterWriter {

  private final FileUtil fileUtil;

  private final LineUtil lineUtil;

  private final FilteredData filteredData;

  private final Claim claim;

  public void writeFilteredData() {
    filteredData.getData().forEach((lineType, values) ->
        Optional.ofNullable(values)
            .filter(list -> !list.isEmpty())
            .ifPresent(list -> writeList(getOuputPath(lineType), list))
    );
  }

  private Path getOuputPath(LineType lineType) {
    String directory = claim.getOutputDirectory();
    String prefix = claim.getOutputFileNamePrefix();
    return Paths.get(directory, prefix + lineType.outputFile());
  }

  private void writeList(Path path, List<Object> values) {
    fileUtil.createParentDirectory(path);
    writeLines(path, lineUtil.toLines(values), claim.isAppendNeeded());
  }

  private void writeLines(Path path, List<String> lines, boolean isAppendNeeded) {
    var options = getWriteOptions(isAppendNeeded);
    try (BufferedWriter writer = Files.newBufferedWriter(path, options)) {
      lines.forEach(line -> {
        try {
          writer.write(line);
          writer.newLine();
        } catch (IOException e) {
          throw new LineNotWriteableException(line, path);
        }
      });
    } catch (IOException e) {
      throw new FileNotOpenableException(path);
    }
  }

  private OpenOption[] getWriteOptions(boolean isAppendNeeded) {
    return isAppendNeeded
        ? new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.APPEND}
        : new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING};
  }
}
