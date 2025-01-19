package ru.shift.filefilterapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.internal.Function;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.shift.filefilterapp.constant.LineType;
import ru.shift.filefilterapp.statistic.Addable;
import ru.shift.filefilterapp.util.LineUtil;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

@Configuration
@RequiredArgsConstructor
public class FileFilterConfig {

  private final LineUtil lineUtil;

  @Bean
  public Map<LineType, Predicate<String>> lineRecognitionRules() {
    Map<LineType, Predicate<String>> recognitionActions = new LinkedHashMap<>();
    recognitionActions.put(LineType.INTEGER, lineUtil::isLong);
    recognitionActions.put(LineType.FLOAT, lineUtil::isDouble);
    recognitionActions.put(LineType.STRING, line -> true);
    return recognitionActions;
  }

  @Bean
  public Map<LineType, Function<String, ?>> parsingActions() {
    Map<LineType, Function<String, ?>> parsingActions = new EnumMap<>(LineType.class);
    parsingActions.put(LineType.INTEGER, Long::parseLong);
    parsingActions.put(LineType.FLOAT, Double::parseDouble);
    parsingActions.put(LineType.STRING, line -> line);
    return parsingActions;
  }

  @Bean
  public Map<LineType, BiConsumer<Addable, Object>> addActions() {
    Map<LineType, BiConsumer<Addable, Object>> addActions = new EnumMap<>(LineType.class);
    addActions.put(LineType.INTEGER, (addable, value) -> addable.addInteger((long) value));
    addActions.put(LineType.FLOAT, (addable, value) -> addable.addFloat((double) value));
    addActions.put(LineType.STRING, (addable, value) -> addable.addString((String) value));
    return addActions;
  }
}
