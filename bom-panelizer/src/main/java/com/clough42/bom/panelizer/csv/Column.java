package com.clough42.bom.panelizer.csv;

import java.util.regex.Pattern;

public class Column {

  private static String DESIGNATOR_REGEX = "^[Dd][Ee][Ss][Ii][Gg][Nn][Aa][Tt][Oo][Rr]$";
  private static Pattern DESIGNATOR_PATTERN = Pattern.compile(DESIGNATOR_REGEX);
  private static String X_COORDINATE_REGEX = "^.*\\s[Xx]$";
  private static Pattern X_COORDINATE_PATTERN = Pattern.compile(X_COORDINATE_REGEX);
  private static String Y_COORDINATE_REGEX = "^.*\\s[Yy]$";
  private static Pattern Y_COORDINATE_PATTERN = Pattern.compile(Y_COORDINATE_REGEX);

  public enum Type {Designator, Text, XCoordinate, YCoordinate}

  private String name;
  private Type type;

  private Column(String name, Type type) {
    this.name = name;
    this.type = type;
  }

  public static Column parse(String name) {
    name = name.trim();
    Type type = Type.Text;

    if (DESIGNATOR_PATTERN.matcher(name).matches()) {
      type = Type.Designator;
    } else if (X_COORDINATE_PATTERN.matcher(name).matches()) {
      type = Type.XCoordinate;
    } else if (Y_COORDINATE_PATTERN.matcher(name).matches()) {
      type = Type.YCoordinate;
    }

    return new Column(name, type);
  }

  public String getName() {
    return name;
  }

  public Type getType() {
    return type;
  }

  @Override
  public String toString() {
    return name;
  }
}
