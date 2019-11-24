package com.clough42.bom.panelizer.csv;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Value {

  private static final String FLOATING_POINT_VALUE_REGEX = "^\\s*(?<number>[0-9]+([.][0-9]+)?)(?<unit>mil|mm)?\\s*$";
  private static Pattern FLOATING_POINT_VALUE_PATTERN = Pattern.compile(FLOATING_POINT_VALUE_REGEX);
  private static final String INTEGER_VALUE_REGEX = "^\\s*(?<number>[0-9]+)(?<unit>mil|mm)?\\s*$";
  private static Pattern INTEGER_VALUE_PATTERN = Pattern.compile(INTEGER_VALUE_REGEX);

  private Column column;
  private String text;

  private Value(Column column, String text) {
    this.column = column;
    this.text = text;
  }

  public static Value parse(String text, Column column) {
    return new Value(column, text);
  }

  @Override
  public String toString() {
    return text;
  }

  public Column getColumn() {
    return column;
  }

  public Integer getIntValue() {
    Matcher matcher = INTEGER_VALUE_PATTERN.matcher(this.text);
    if (matcher.matches()) {
      String numberText = matcher.group("number");
      return Integer.parseInt(numberText);
    }
    throw new IllegalArgumentException("expected an integer value: " + this.text);
  }

  public Double getDoubleValue() {
    Matcher matcher = FLOATING_POINT_VALUE_PATTERN.matcher(this.text);
    if (matcher.matches()) {
      String numberText = matcher.group("number");
      return Double.parseDouble(numberText);
    }
    throw new IllegalArgumentException("expected a numeric floating point value: " + this.text);
  }

  public String getUnit() {
    Matcher matcher = FLOATING_POINT_VALUE_PATTERN.matcher(this.text);
    String unit = null;
    if (matcher.matches()) {
      unit = matcher.group("unit");
    }
    return unit;
  }
}
