package com.clough42.bom.panelizer.csv;

public class Value {

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
}
