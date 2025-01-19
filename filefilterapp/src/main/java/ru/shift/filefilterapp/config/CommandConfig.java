package ru.shift.filefilterapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.shift.filefilterapp.command.Claim;
import ru.shift.filefilterapp.constant.OptionType;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Configuration
public class CommandConfig {

  @Bean
  public Map<String, BiConsumer<Claim, String>> valueOptions() {
    Map<String, BiConsumer<Claim, String>> valueOptions = new HashMap<>();
    valueOptions.put("-o", Claim::setOutputDirectory);
    valueOptions.put("-p", Claim::setOutputFileNamePrefix);
    return valueOptions;
  }

  @Bean
  public Map<String, Consumer<Claim>> flagOptions() {
    Map<String, Consumer<Claim>> flagOptions = new HashMap<>();
    flagOptions.put("-a", Claim::enableAppendNeeded);
    flagOptions.put("-s", Claim::setShortStatistic);
    flagOptions.put("-f", Claim::setFullStatistic);
    return flagOptions;
  }

  @Bean
  public Map<OptionType, Predicate<String>> optionRecognitionRules() {
    Map<String, BiConsumer<Claim, String>> valueOptions = valueOptions();
    Map<String, Consumer<Claim>> flagOptions = flagOptions();
    Map<OptionType, Predicate<String>> recognitionActions = new LinkedHashMap<>();
    recognitionActions.put(OptionType.VALUE, valueOptions::containsKey);
    recognitionActions.put(OptionType.FLAG, flagOptions::containsKey);
    return recognitionActions;
  }
}