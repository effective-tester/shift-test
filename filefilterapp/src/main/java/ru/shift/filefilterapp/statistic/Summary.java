package ru.shift.filefilterapp.statistic;

import java.util.Optional;

public abstract class Summary implements Addable, Summarizable {

  protected long integerCount = 0;

  protected long floatCount = 0;

  protected long stringCount = 0;

  public void print() {
    summarize().forEach((summary, count) ->
        Optional.of(count)
            .filter(cnt -> cnt > 0)
            .ifPresent((ignored) -> System.out.println(summary))
    );
  }
}
