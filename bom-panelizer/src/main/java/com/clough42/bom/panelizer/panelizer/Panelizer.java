package com.clough42.bom.panelizer.panelizer;

import java.io.File;

public class Panelizer {

  public Panelizer() {
  }
  
  public void panelize(File bomFile, File centroidFile, File panelFile, File outputBomFile, File outputCentroidFile) {
    System.out.println("bomFile: " + bomFile.getAbsolutePath());
    System.out.println("centroidFile: " + centroidFile.getAbsolutePath());
    System.out.println("panelFile: " + panelFile.getAbsolutePath());
    System.out.println("outputBomFile: " + outputBomFile.getAbsolutePath());
    System.out.println("outputCentroidFile: " + outputCentroidFile.getAbsolutePath());
  }
  
}
