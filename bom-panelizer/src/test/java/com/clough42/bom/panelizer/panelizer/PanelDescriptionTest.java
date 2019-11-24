package com.clough42.bom.panelizer.panelizer;

import com.clough42.bom.panelizer.TestFiles;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

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

    for (BoardLocation location : uut) {
      assertThat(location.getBase()).isGreaterThan(900);
      assertThat(location.getX()).isGreaterThanOrEqualTo(0);
      assertThat(location.getY()).isGreaterThanOrEqualTo(0);
      count++;
    }

    assertThat(count).isGreaterThan(1);
  }

}