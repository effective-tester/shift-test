package ru.shift.filefilterapp.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.shift.filefilterapp.constant.OptionType;
import ru.shift.filefilterapp.exception.OptionIncorrectException;
import ru.shift.filefilterapp.exception.ValueOptionIncorrectException;
import ru.shift.filefilterapp.util.CommandUtil;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class OptionProcessor {

  private final CommandUtil commandUtil;

  private final Claim claim;

  private final Map<OptionType, Predicate<String>> recognitionRules;

  private final Map<String, BiConsumer<Claim, String>> valueOptions;

  private final Map<String, Consumer<Claim>> flagOptions;

  public int process(String[] args, int iterator) {
    String option = args[iterator];
    var type = recognize(option);
    return handle(args, iterator, option, type);
  }

  private OptionType recognize(String option) {
    return recognitionRules.entrySet().stream()
        .filter(entry -> entry.getValue().test(option))
        .map(Map.Entry::getKey)
        .findFirst()
        .orElse(OptionType.UNKNOWN);
  }

  private int handle(String[] args, int iterator, String option, OptionType type) {
    Object value = null;
    if (type == OptionType.VALUE) {
      value = Optional.ofNullable(iterator != args.length - 1 ? args[++iterator] : null)
          .filter(nextArg -> !commandUtil.isOption(nextArg))
          .orElseThrow(() -> new ValueOptionIncorrectException(option));
    }
    set(option, type, value);
    return iterator;
  }

  public void set(String option, OptionType type, Object value) {
    switch (type) {
      case VALUE -> valueOptions.get(option).accept(claim, (String) value);
      case FLAG -> flagOptions.get(option).accept(claim);
      default -> throw new OptionIncorrectException(option);
    }
  }
}
