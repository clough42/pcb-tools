package com.clough42.bom.panelizer;

import com.clough42.bom.panelizer.panelizer.Panelizer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BomPanelizerTest {

  @Mock
  private Panelizer mockPanelizer;
  private TestFiles testFiles = new TestFiles();

  public BomPanelizerTest() throws IOException {
  }

  @Before
  public void setUp() {
    BomPanelizer.setPanelizerForTesting(mockPanelizer);
  }

  @After
  public void TearDown() throws Exception {
    BomPanelizer.setPanelizerForTesting(new Panelizer());
    testFiles.cleanUp();
  }

  @Test
  public void executeMainNoArgs() throws IOException {
    BomPanelizer.main(null);

    verify(mockPanelizer, never()).panelize(any(File.class), any(File.class), any(File.class), any(File.class), any(File.class));
  }

  @Test
  public void executeMainWithArgs() throws IOException {
    BomPanelizer.main(new String[]{
      "-b", testFiles.SAMPLE_BOM_FILENAME,
      "-c", testFiles.SAMPLE_PICK_PLACE_FILENAME,
      "-p", testFiles.SAMPLE_PANEL_FILENAME,
      "-o", testFiles.OUTPUT_DIR_NAME
    });
    verify(mockPanelizer).panelize(any(File.class), any(File.class), any(File.class), any(File.class), any(File.class));
  }

  @Test
  public void executeMainWithBadBomFile() throws IOException {
    BomPanelizer.main(new String[]{
      "-b", testFiles.SAMPLE_BOM_FILENAME + "X",
      "-c", testFiles.SAMPLE_PICK_PLACE_FILENAME,
      "-p", testFiles.SAMPLE_PANEL_FILENAME,
      "-o", testFiles.OUTPUT_DIR_NAME
    });
    verify(mockPanelizer, never()).panelize(any(File.class), any(File.class), any(File.class), any(File.class), any(File.class));
  }

  @Test
  public void executeMainWithBadCentroidFile() throws IOException {
    BomPanelizer.main(new String[]{
      "-b", testFiles.SAMPLE_BOM_FILENAME,
      "-c", testFiles.SAMPLE_PICK_PLACE_FILENAME + "X",
      "-p", testFiles.SAMPLE_PANEL_FILENAME,
      "-o", testFiles.OUTPUT_DIR_NAME
    });
    verify(mockPanelizer, never()).panelize(any(File.class), any(File.class), any(File.class), any(File.class), any(File.class));
  }

  @Test
  public void executeMainWithBadPanelFile() throws IOException {
    BomPanelizer.main(new String[]{
      "-b", testFiles.SAMPLE_BOM_FILENAME,
      "-c", testFiles.SAMPLE_PICK_PLACE_FILENAME,
      "-p", testFiles.SAMPLE_PANEL_FILENAME + "X",
      "-o", testFiles.OUTPUT_DIR_NAME
    });
    verify(mockPanelizer, never()).panelize(any(File.class), any(File.class), any(File.class), any(File.class), any(File.class));
  }

  @Test
  public void executeMainWithBadOutputDirectory() throws IOException {
    BomPanelizer.main(new String[]{
      "-b", testFiles.SAMPLE_BOM_FILENAME,
      "-c", testFiles.SAMPLE_PICK_PLACE_FILENAME,
      "-p", testFiles.SAMPLE_PANEL_FILENAME,
      "-o", "ZZZ:/notreal"
    });
    verify(mockPanelizer, never()).panelize(any(File.class), any(File.class), any(File.class), any(File.class), any(File.class));
  }
}
