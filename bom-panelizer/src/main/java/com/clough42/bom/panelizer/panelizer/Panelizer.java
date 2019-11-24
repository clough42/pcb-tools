package com.clough42.bom.panelizer.panelizer;

import com.clough42.bom.panelizer.csv.CSVFile;

import java.io.File;
import java.io.IOException;

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
    inputCentroid.save(outputCentroidFile);
  }

  private CSVFile panelizeBom(CSVFile inputBom, PanelDescription panel) {
    return inputBom;
  }

  private CSVFile panelizeCentroid(CSVFile inputCentroid, PanelDescription panel) {
    return inputCentroid;
  }

}
