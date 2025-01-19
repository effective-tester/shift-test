package ru.shift.filefilterapp.constant;

public enum LineType {

  INTEGER("integers.txt"),

  FLOAT("floats.txt"),

  STRING("strings.txt");

  private final String outputFile;

  LineType(String outputFile) {
    this.outputFile = outputFile;
  }

  public String outputFile() {
    return outputFile;
  }
}
