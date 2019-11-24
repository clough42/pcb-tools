package com.clough42.bom.panelizer;

import com.clough42.bom.panelizer.panelizer.Panelizer;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.nio.file.Files;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BomPanelizerTest {

  @Mock
  private Panelizer mockPanelizer;
  private File tempDir;

  @Before
  public void setUp() throws Exception {
    BomPanelizer.setPanelizerForTesting(mockPanelizer);
    tempDir = Files.createTempDirectory("bomPanelizer-test-").toFile();
  }

  @After
  public void TearDown() throws Exception {
    BomPanelizer.setPanelizerForTesting(new Panelizer());
    FileUtils.deleteDirectory(tempDir);
  }

  @Test
  public void executeMainNoArgs() {
    BomPanelizer.main(null);

    verify(mockPanelizer, never()).panelize(any(File.class), any(File.class), any(File.class), any(File.class), any(File.class));
  }

  @Test
  public void executeMainWithArgs() {
    BomPanelizer.main(new String[]{
      "-b", "target/test-classes/sample-bom.csv",
      "-c", "target/test-classes/sample-pick-place.csv",
      "-p", "target/test-classes/sample-panel.csv",
      "-o", new File(tempDir, "output").toString()
    });
    verify(mockPanelizer).panelize(any(File.class), any(File.class), any(File.class), any(File.class), any(File.class));
  }

  @Test(expected = IllegalArgumentException.class)
  public void executeMainWithBadBomFile() {
    BomPanelizer.main(new String[]{
      "-b", "target/test-classes/Xsample-bom.csv",
      "-c", "target/test-classes/sample-pick-place.csv",
      "-p", "target/test-classes/sample-panel.csv",
      "-o", new File(tempDir, "output").toString()
    });
    verify(mockPanelizer).panelize(any(File.class), any(File.class), any(File.class), any(File.class), any(File.class));
  }

  @Test(expected = IllegalArgumentException.class)
  public void executeMainWithBadCentroidFile() {
    BomPanelizer.main(new String[]{
      "-b", "target/test-classes/sample-bom.csv",
      "-c", "target/test-classes/Xsample-pick-place.csv",
      "-p", "target/test-classes/sample-panel.csv",
      "-o", new File(tempDir, "output").toString()
    });
    verify(mockPanelizer).panelize(any(File.class), any(File.class), any(File.class), any(File.class), any(File.class));
  }

  @Test(expected = IllegalArgumentException.class)
  public void executeMainWithBadPanelFile() {
    BomPanelizer.main(new String[]{
      "-b", "target/test-classes/sample-bom.csv",
      "-c", "target/test-classes/sample-pick-place.csv",
      "-p", "target/test-classes/Xsample-panel.csv",
      "-o", new File(tempDir, "output").toString()
    });
    verify(mockPanelizer).panelize(any(File.class), any(File.class), any(File.class), any(File.class), any(File.class));
  }

  @Test(expected = IllegalArgumentException.class)
  public void executeMainWithBadOutputDirectory() {
    BomPanelizer.main(new String[]{
      "-b", "target/test-classes/sample-bom.csv",
      "-c", "target/test-classes/sample-pick-place.csv",
      "-p", "target/test-classes/sample-panel.csv",
      "-o", "ZZZ:/notreal"
    });
    verify(mockPanelizer).panelize(any(File.class), any(File.class), any(File.class), any(File.class), any(File.class));
  }
}
