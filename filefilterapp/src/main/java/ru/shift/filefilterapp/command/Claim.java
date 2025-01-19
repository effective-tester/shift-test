package ru.shift.filefilterapp.command;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.shift.filefilterapp.constant.SummaryType;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class Claim {

  private String outputDirectory = ".";

  private String outputFileNamePrefix = "";

  private boolean appendNeeded = false;

  private SummaryType summaryType = SummaryType.SHORT;

  private final List<String> inputFiles = new ArrayList<>();

  public void enableAppendNeeded() {
    setAppendNeeded(Boolean.TRUE);
  }

  public void setShortStatistic() {
    setSummaryType(SummaryType.SHORT);
  }

  public void setFullStatistic() {
    setSummaryType(SummaryType.FULL);
  }

  public void addInputFile(String file) {
    this.inputFiles.add(file);
  }
}
