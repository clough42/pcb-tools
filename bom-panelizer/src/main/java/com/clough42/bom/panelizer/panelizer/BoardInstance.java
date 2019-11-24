package com.clough42.bom.panelizer.panelizer;

import com.clough42.bom.panelizer.csv.Row;
import com.clough42.bom.panelizer.csv.Value;

public class BoardInstance {

  private static final String DESIGNATOR_BASE = "DesignatorBase";
  private static final String OFFSET_X = "Offset X";
  private static final String OFFSET_Y = "Offset Y";

  private int base;
  private double x;
  private double y;

  private BoardInstance(int base, double x, double y) {
    this.base = base;
    this.x = x;
    this.y = y;
  }

  public static BoardInstance fromRow(Row row) {
    Integer base = null;
    Double x = null;
    Double y = null;

    for (Value val : row) {
      String name = val.getColumn().getName();
      if (DESIGNATOR_BASE.equals(name)) {
        base = val.getIntValue();
      }
      if (OFFSET_X.equals(name)) {
        x = val.getDoubleValue();
      }
      if (OFFSET_Y.equals(name)) {
        y = val.getDoubleValue();
      }
    }

    if (base == null) {
      throw new IllegalArgumentException("Missing designator base in row: " + row);
    }
    if (x == null) {
      throw new IllegalArgumentException("Missing X offset in row: " + row);
    }
    if (y == null) {
      throw new IllegalArgumentException("Missing Y offset in row: " + row);
    }

    return new BoardInstance(base, x, y);
  }

  public int getBase() {
    return base;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }
}
