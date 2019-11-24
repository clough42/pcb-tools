package com.clough42.bom.panelizer.panelizer;

import com.clough42.bom.panelizer.csv.CSVFile;
import com.clough42.bom.panelizer.csv.Row;
import com.clough42.bom.panelizer.csv.Value;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

public class Panelizer {

  public Panelizer() {
  }

  public void panelize(File bomFile, File centroidFile, File panelFile, File outputBomFile, File outputCentroidFile) throws IOException {
    CSVFile inputBom = CSVFile.load(bomFile);
    CSVFile inputCentroid = CSVFile.load(centroidFile);
    PanelDescription panel = PanelDescription.load(panelFile);

    CSVFile outputBom = panelizeBom(inputBom, panel);
    CSVFile outputCentroid = panelizeCentroid(inputCentroid, panel);

    outputBom.save(outputBomFile);
    outputCentroid.save(outputCentroidFile);
  }

  private CSVFile panelizeBom(CSVFile inputBom, PanelDescription panel) {
    CSVFile outputBom = inputBom.derive();

    for (Row row : inputBom) {
      outputBom.add(expandBomRow(row, panel));
    }

    return outputBom;
  }

  private Row expandBomRow(Row inRow, PanelDescription panel) {
    Row outRow = new Row();

    for (Value inValue : inRow) {
      switch (inValue.getColumn().getType()) {
        case Designator:
          outRow.add(inValue.offsetBomDesignators(panel));
          break;
        default:
          outRow.add(inValue);
          break;
      }
    }

    return outRow;
  }

  private CSVFile panelizeCentroid(CSVFile inputCentroid, PanelDescription panel) {
    CSVFile outputCentroid = inputCentroid.derive();

    for (Row inRow : inputCentroid) {
      if (inRow.size() > 1) {
        outputCentroid.addAll(expandAndOffsetCentroidRow(inRow, panel));
      }
    }

    return outputCentroid;
  }

  private Collection<Row> expandAndOffsetCentroidRow(Row inRow, PanelDescription panel) {
    return panel.stream()
      .map(instance -> offsetCentroidRow(inRow, instance))
      .collect(Collectors.toList());
  }

  private Row offsetCentroidRow(Row inRow, BoardInstance instance) {
    Row outRow = new Row();

    for (Value inValue : inRow) {
      switch (inValue.getColumn().getType()) {
        case Designator:
          outRow.add(inValue.offsetBomDesignator(instance));
          break;
        case XCoordinate:
          outRow.add(inValue.offsetValue(instance.getX()));
          break;
        case YCoordinate:
          outRow.add(inValue.offsetValue(instance.getY()));
          break;
        default:
          outRow.add(inValue);
          break;
      }
    }

    return outRow;
  }

}
