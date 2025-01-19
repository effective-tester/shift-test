package ru.shift.filefilterapp.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.shift.filefilterapp.util.CommandUtil;

@Component
@RequiredArgsConstructor
public class CommandProcessor {

  private final OptionProcessor optionProcessor;

  private final CommandUtil commandUtil;

  private final Claim claim;

  public void process(String[] args) {
    for (int i = 0; i < args.length; i++) {
      String arg = args[i];
      if (commandUtil.isOption(arg)) {
        i = optionProcessor.process(args, i);
      } else {
        claim.addInputFile(arg);
      }
    }
  }
}

