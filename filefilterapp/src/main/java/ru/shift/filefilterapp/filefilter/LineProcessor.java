package ru.shift.filefilterapp.filefilter;

import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;
import ru.shift.filefilterapp.constant.LineType;
import ru.shift.filefilterapp.statistic.Addable;
import ru.shift.filefilterapp.statistic.Statistic;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class LineProcessor {

  private final FilteredData filteredData;

  private final Statistic statistic;

  private final Map<LineType, Predicate<String>> recognitionRules;

  private final Map<LineType, Function<String, ?>> parsingActions;

  private final Map<LineType, BiConsumer<Addable, Object>> addActions;

  public void process(String line) {
    var type = recognize(line);
    var value = parse(line, type);
    add(filteredData, value, type);
    add(statistic.get(), value, type);
  }

  public LineType recognize(String line) {
    return recognitionRules.entrySet().stream()
        .filter(entry -> entry.getValue().test(line))
        .map(Map.Entry::getKey)
        .findFirst()
        .orElse(LineType.STRING);
  }

  private Object parse(String line, LineType type) {
    return parsingActions.get(type).apply(line);
  }

  private void add(Addable addable, Object value, LineType type) {
    addActions.get(type).accept(addable, value);
  }
}
