package ru.shift.filefilterapp.statistic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class FullSummary extends Summary {

  private long integerMin = Long.MAX_VALUE;

  private long integerMax = Long.MIN_VALUE;

  private long integerSum = 0;

  private double floatMin = Double.MAX_VALUE;

  private double floatMax = -Double.MIN_VALUE;

  private double floatSum = 0;

  private long stringMinLength = Long.MAX_VALUE;

  private long stringMaxLength = Long.MIN_VALUE;

  @Override
  public void addInteger(long value) {
    integerCount++;
    integerMin = Math.min(integerMin, value);
    integerMax = Math.max(integerMax, value);
    integerSum += value;
  }

  @Override
  public void addFloat(double value) {
    floatCount++;
    floatMin = Math.min(floatMin, value);
    floatMax = Math.max(floatMax, value);
    floatSum += value;
  }

  @Override
  public void addString(String value) {
    stringCount++;
    long length = value.length();
    stringMinLength = Math.min(stringMinLength, length);
    stringMaxLength = Math.max(stringMaxLength, length);
  }

  @Override
  public Map<String, Long> summarize() {
    Map<String, Long> summary = new LinkedHashMap<>();
    String integerStats = """
        Целые числа:
          Количество: %d
          Минимум: %d
          Максимум: %d
          Сумма: %d
          Среднее: %.4f
        """.formatted(integerCount, integerMin, integerMax, integerSum, integerSum / (double) integerCount);
    String floatStats = """
        Вещественные числа:
          Количество: %d
          Минимум: %.4f
          Максимум: %.4f
          Сумма: %.4f
          Среднее: %.4f
        """.formatted(floatCount, floatMin, floatMax, floatSum, floatSum / floatCount);
    String stringStats = """
        Строки:
          Количество: %d
          Минимальная длина: %d
          Максимальная длина: %d
        """.formatted(stringCount, stringMinLength, stringMaxLength);
    summary.put(integerStats, integerCount);
    summary.put(floatStats, floatCount);
    summary.put(stringStats, stringCount);
    return summary;
  }
}
