package com.clough42.bom.panelizer.panelizer;

import com.clough42.bom.panelizer.TestFiles;
import com.clough42.bom.panelizer.csv.CSVFile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;

public class PanelDescriptionTest {

  private TestFiles testFiles;
  private PanelDescription uut;

  @Before
  public void setUp() throws IOException {
    testFiles = new TestFiles();
    uut = PanelDescription.load(testFiles.SAMPLE_PANEL);
  }

  @After
  public void tearDown() throws IOException {
    testFiles.cleanUp();
  }

  @Test
  public void testAllRows() {
    int count = 0;

    for (BoardInstance location : uut) {
      assertThat(location.getBase()).isGreaterThan(900);
      assertThat(location.getX()).isGreaterThanOrEqualTo(0);
      assertThat(location.getY()).isGreaterThanOrEqualTo(0);
      count++;
    }

    assertThat(count).isGreaterThan(1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMissingDesignatorBase() throws IOException {
    new PanelDescription(CSVFile.load(new StringReader(
      "blah,Offset X,Offset Y\n" +
        "1000, 0mil, 0mil\n" +
        "2000, 3000mil, 0mil"
    )));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMissingOffsetX() throws IOException {
    new PanelDescription(CSVFile.load(new StringReader(
      "DesignatorBase,blah,Offset Y\n" +
        "1000, 0mil, 0mil\n" +
        "2000, 3000mil, 0mil"
    )));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMissingOffsetY() throws IOException {
    new PanelDescription(CSVFile.load(new StringReader(
      "DesignatorBase,Offset X,blah\n" +
        "1000, 0mil, 0mil\n" +
        "2000, 3000mil, 0mil"
    )));
  }

}