package ru.shift.filefilterapp.statistic;

import java.util.Map;

public interface Summarizable {

  Map<String, Long> summarize();
}
