package ru.shift.filefilterapp.util;

import org.springframework.stereotype.Component;

@Component
public class CommandUtil {

  public boolean isOption(String arg) {
    return arg.startsWith("-");
  }
}
