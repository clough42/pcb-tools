package com.clough42.bom.panelizer.csv;

import com.clough42.bom.panelizer.TestFiles;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CSVFileTest {

  private TestFiles testFiles;

  @Before
  public void setUp() throws IOException {
    testFiles = new TestFiles();
  }

  @After
  public void tearDown() throws IOException {
    testFiles.cleanUp();
  }

  @Test
  public void loadSampleBom() throws Exception {
    CSVFile uut = CSVFile.load(testFiles.SAMPLE_BOM);

    assertThat(uut.getColumns()).isNotNull().isNotEmpty();
    assertThat(uut.getColumns().size()).isEqualTo(4);

    assertThat(uut.getColumns().get(0).getName()).isEqualTo("Comment");
    assertThat(uut.getColumns().get(0).getType()).isEqualTo(Column.Type.Text);
    assertThat(uut.getColumns().get(1).getName()).isEqualTo("Designator");
    assertThat(uut.getColumns().get(1).getType()).isEqualTo(Column.Type.Designator);
    assertThat(uut.getColumns().get(2).getName()).isEqualTo("Footprint");
    assertThat(uut.getColumns().get(2).getType()).isEqualTo(Column.Type.Text);
    assertThat(uut.getColumns().get(3).getName()).isEqualTo("LCSC Part #");
    assertThat(uut.getColumns().get(3).getType()).isEqualTo(Column.Type.Text);

    assertThat(uut.size()).isEqualTo(16);

    assertThat(uut.get(0).get(0).toString()).isEqualTo("10k");
    assertThat(uut.get(0).get(0).getColumn().getType()).isEqualTo(Column.Type.Text);
    assertThat(uut.get(0).get(1).toString()).isEqualTo("R1, R2, R3, R4, R5, R6, R10, R11");
    assertThat(uut.get(0).get(1).getColumn().getType()).isEqualTo(Column.Type.Designator);
    assertThat(uut.get(0).get(2).toString()).isEqualTo("RESC1609X50X30NL10T20");
    assertThat(uut.get(0).get(2).getColumn().getType()).isEqualTo(Column.Type.Text);
    assertThat(uut.get(0).get(3).toString()).isEqualTo("C25804");
    assertThat(uut.get(0).get(3).getColumn().getType()).isEqualTo(Column.Type.Text);
  }

  @Test
  public void testIterator() throws Exception {
    CSVFile uut = CSVFile.load(testFiles.SAMPLE_BOM);

    int count = 0;
    for (Row row : uut) {
      count++;
    }

    assertThat(count).isGreaterThan(5);
  }

  @Test
  public void testSave() throws IOException {
    CSVFile uut = CSVFile.load(testFiles.SAMPLE_BOM);
    testFiles.OUTPUT_DIR.mkdir();
    assertThat(testFiles.OUTPUT_BOM.exists()).isFalse();

    uut.save(testFiles.OUTPUT_BOM);

    assertThat(testFiles.OUTPUT_BOM.exists()).isTrue();
  }

  @Test
  public void testDerive() throws IOException {
    CSVFile uut = CSVFile.load(testFiles.SAMPLE_BOM);

    CSVFile ret = uut.derive();

    assertThat(ret.getColumns()).isEqualTo(uut.getColumns());
  }

}