package com.clough42.bom.panelizer.csv;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Designator {

  private static final String LETTER = "letter";
  private static final String NUMBER = "number";

  private static final String DESIGNATOR_REGEX = "^(?<" + LETTER + ">[A-Za-z0-9]*[A-Za-z])(?<" + NUMBER + ">[0-9]+)$";
  private static final Pattern regex = Pattern.compile(DESIGNATOR_REGEX);

  private String letter;
  private int number;

  private Designator(String letter, int number) {
    this.letter = letter;
    this.number = number;
  }

  static Designator parse(String raw) {
    String trimmed = raw.trim();
    Matcher m = regex.matcher(trimmed);
    if (!m.matches()) {
      throw new IllegalArgumentException("raw designator is not in the correct format: " + trimmed);
    }

    return new Designator(m.group(LETTER), Integer.parseInt(m.group(NUMBER)));
  }

  public Designator offset(int base) {
    return new Designator(letter, number + base);
  }

  @Override
  public String toString() {
    return letter + number;
  }

}
