package ru.shift.filefilterapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.shift.filefilterapp.constant.SummaryType;
import ru.shift.filefilterapp.statistic.FullSummary;
import ru.shift.filefilterapp.statistic.ShortSummary;
import ru.shift.filefilterapp.statistic.Summary;

import java.util.EnumMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class StatisticConfig {

  private final ShortSummary shortSummary;

  private final FullSummary fullSummary;

  @Bean
  public Map<SummaryType, Summary> summaryRealizations() {
    Map<SummaryType, Summary> statisticRealizations = new EnumMap<>(SummaryType.class);
    statisticRealizations.put(SummaryType.SHORT, shortSummary);
    statisticRealizations.put(SummaryType.FULL, fullSummary);
    return statisticRealizations;
  }
}
