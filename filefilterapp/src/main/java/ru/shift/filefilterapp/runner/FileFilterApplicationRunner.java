package ru.shift.filefilterapp.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.shift.filefilterapp.command.Claim;
import ru.shift.filefilterapp.command.CommandProcessor;
import ru.shift.filefilterapp.exception.InputFilesNotFoundException;
import ru.shift.filefilterapp.filefilter.FilesFilterProcessor;
import ru.shift.filefilterapp.statistic.Statistic;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class FileFilterApplicationRunner implements CommandLineRunner {

  private final CommandProcessor commandProcessor;

  private final FilesFilterProcessor filesFilterProcessor;

  private final Statistic statistic;

  private final Claim claim;

  @Override
  public void run(String... args) {
    commandProcessor.process(args);
    Optional.ofNullable(claim.getInputFiles())
        .filter(files -> !files.isEmpty())
        .orElseThrow(InputFilesNotFoundException::new);
    filesFilterProcessor.process();
    statistic.print();
  }
}
