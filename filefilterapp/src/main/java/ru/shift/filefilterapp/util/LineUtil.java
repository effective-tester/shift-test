package ru.shift.filefilterapp.util;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
public class LineUtil {

  public boolean isLong(String line) {
    return tryParse(line, Long::parseLong).isPresent();
  }

  public boolean isDouble(String line) {
    return tryParse(line, Double::parseDouble).isPresent();
  }

  private <T> Optional<T> tryParse(String line, Function<String, T> parser) {
    try {
      return Optional.of(line)
          .map(String::trim)
          .filter(s -> !s.isEmpty())
          .map(parser);
    } catch (NumberFormatException | NullPointerException ignored) {
      return Optional.empty();
    }
  }

  public List<String> toLines(List<Object> values) {
    return values.stream()
        .map(Object::toString)
        .toList();
  }
}
