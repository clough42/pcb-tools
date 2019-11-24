package com.clough42.bom.panelizer.panelizer;

import com.clough42.bom.panelizer.csv.BomFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Panelizer {

  public Panelizer() {
  }
  
  public void panelize(File bomFile, File centroidFile, File panelFile, File outputBomFile, File outputCentroidFile) throws IOException {
    System.out.println("bomFile: " + bomFile.getAbsolutePath());
    System.out.println("centroidFile: " + centroidFile.getAbsolutePath());
    System.out.println("panelFile: " + panelFile.getAbsolutePath());
    System.out.println("outputBomFile: " + outputBomFile.getAbsolutePath());
    System.out.println("outputCentroidFile: " + outputCentroidFile.getAbsolutePath());
    
    BomFile inputBom = BomFile.load(new FileReader(bomFile));
    BomFile inputCentroid = BomFile.load(new FileReader(centroidFile));
  }
  
}
