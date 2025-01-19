package ru.shift.filefilterapp.statistic;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ShortSummary extends Summary {

  @Override
  public void addInteger(long value) {
    integerCount++;
  }

  @Override
  public void addFloat(double value) {
    floatCount++;
  }

  @Override
  public void addString(String value) {
    stringCount++;
  }

  @Override
  public Map<String, Long> summarize() {
    Map<String, Long> summary = new LinkedHashMap<>();
    String integerStats = String.format("Целые числа: %d", integerCount);
    String floatStats = String.format("Вещественные числа: %d", floatCount);
    String stringStats = String.format("Строки: %d", stringCount);
    summary.put(integerStats, integerCount);
    summary.put(floatStats, floatCount);
    summary.put(stringStats, stringCount);
    return summary;
  }
}
