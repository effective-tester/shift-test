package ru.shift.filefilterapp.statistic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.shift.filefilterapp.command.Claim;
import ru.shift.filefilterapp.constant.SummaryType;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class Statistic {

  private final Map<SummaryType, Summary> summaryRealizations;

  private final Claim claim;

  public void print() {
    get().print();
  }

  public Summary get() {
    return summaryRealizations.get(claim.getSummaryType());
  }
}
