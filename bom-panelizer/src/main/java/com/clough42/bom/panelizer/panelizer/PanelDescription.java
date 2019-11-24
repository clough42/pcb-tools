package com.clough42.bom.panelizer.panelizer;

import com.clough42.bom.panelizer.csv.CSVFile;
import com.clough42.bom.panelizer.csv.Row;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PanelDescription extends ArrayList<BoardInstance> {

  private PanelDescription(CSVFile csv) {
    for (Row row : csv) {
      add(BoardInstance.fromRow(row));
    }
  }

  public static PanelDescription load(File file) throws IOException {
    CSVFile csv = CSVFile.load(file);
    return new PanelDescription(csv);
  }

}
