package com.clough42.bom.panelizer.csv;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Row extends ArrayList<Value> {

  public static Row parse(Iterable<String> record, List<Column> columns) {
    Row row = new Row();

    Iterator<Column> columnIter = columns.iterator();
    for (String text : record) {
      Column column = columnIter.next();

      row.add(Value.parse(text, column));
    }

    return row;
  }
}
