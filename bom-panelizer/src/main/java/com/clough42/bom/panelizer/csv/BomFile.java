package com.clough42.bom.panelizer.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class BomFile {

  private List<Column> columns;
  private List<Row> rows;

  private BomFile(CSVParser parser) {
    rows = new ArrayList<>();

    for (CSVRecord record : parser) {
      if (columns == null) {
        columns = readColumns(record);
      } else {
        rows.add(readRow(record));
      }
    }
  }

  private List<Column> readColumns(CSVRecord record) {
    ArrayList<Column> list = new ArrayList<>();
    for (String name : record) {
      list.add(Column.parse(name));
    }
    return list;
  }

  private Row readRow(CSVRecord record) {
    return Row.parse(record, columns);
  }

  public static BomFile load(Reader reader) throws IOException {
    try (CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT)) {
      return new BomFile(parser);
    }
  }

  public List<Column> getColumns() {
    return columns;
  }

  public List<Row> getRows() {
    return rows;
  }
}
