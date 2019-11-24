package com.clough42.bom.panelizer.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVFile implements Iterable<Row> {

  private List<Column> columns;
  private List<Row> rows;

  private CSVFile(CSVParser parser) {
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

  public static CSVFile load(File file) throws IOException {
    try (Reader reader = new InputStreamReader(new FileInputStream(file), "Windows-1252")) {
      return load(reader);
    }
  }

  public static CSVFile load(Reader reader) throws IOException {
    try (CSVParser parser = CSVFormat.EXCEL.parse(reader)) {
      return new CSVFile(parser);
    }
  }

  public void save(File file) throws IOException {
    try (Writer writer = new OutputStreamWriter(new FileOutputStream(file, false), "Windows-1252")) {
      save(writer);
    }
  }

  public void save(Writer writer) throws IOException {
    try (CSVPrinter printer = CSVFormat.EXCEL.print(writer)) {
      // write header row
      printer.printRecord(columns);
      // write data
      for (Row row : this) {
        printer.printRecord(row);
      }
    }
  }

  public List<Column> getColumns() {
    return columns;
  }

  public List<Row> getRows() {
    return rows;
  }

  @Override
  public Iterator<Row> iterator() {
    return getRows().iterator();
  }
}
