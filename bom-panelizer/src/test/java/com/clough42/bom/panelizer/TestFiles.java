package com.clough42.bom.panelizer;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestFiles {

  public final String SAMPLE_BOM_FILENAME = "target/test-classes/sample-bom.csv";
  public final String SAMPLE_PICK_PLACE_FILENAME = "target/test-classes/sample-pick-place.csv";
  public final String SAMPLE_PANEL_FILENAME = "target/test-classes/sample-panel.csv";
  
  public final File SAMPLE_BOM = new File(SAMPLE_BOM_FILENAME);
  public final File SAMPLE_PICK_PLACE = new File(SAMPLE_PICK_PLACE_FILENAME);
  public final File SAMPLE_PANEL = new File(SAMPLE_PANEL_FILENAME);
  
  private final File TEMP_DIR = Files.createTempDirectory("bomPanelizer-test-").toFile();
  
  public final File OUTPUT_DIR = new File(TEMP_DIR, "output");
  public final String OUTPUT_DIR_NAME = OUTPUT_DIR.toString();

  public final File OUTPUT_BOM = new File(OUTPUT_DIR, SAMPLE_BOM.getName());
  public final File OUTPUT_PICK_PLACE = new File(OUTPUT_DIR, SAMPLE_PICK_PLACE.getName());
  
  public TestFiles() throws IOException {
  }
  
  public void cleanUp() throws IOException {
    FileUtils.deleteDirectory(TEMP_DIR);
  }
  
}
