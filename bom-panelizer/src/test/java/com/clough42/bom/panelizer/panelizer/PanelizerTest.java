package com.clough42.bom.panelizer.panelizer;

import com.clough42.bom.panelizer.TestFiles;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class PanelizerTest {
  
  private Panelizer uut;
  private TestFiles testFiles;

  @Before
  public void setUp() throws IOException {
    testFiles = new TestFiles();
    uut = new Panelizer();
  }
  
  @After
  public void tearDown() throws IOException {
    testFiles.cleanUp();
  }
  
  @Test
  public void testHappyDay() throws IOException {
    testFiles.OUTPUT_DIR.mkdir();
    
    uut.panelize(
      testFiles.SAMPLE_BOM,
      testFiles.SAMPLE_PICK_PLACE,
      testFiles.SAMPLE_PANEL,
      testFiles.OUTPUT_BOM,
      testFiles.OUTPUT_PICK_PLACE
    );
  }

}