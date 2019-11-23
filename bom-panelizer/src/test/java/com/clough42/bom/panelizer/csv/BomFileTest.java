package com.clough42.bom.panelizer.csv;

import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class BomFileTest {

  @Test
  public void loadSampleBom() throws Exception {
    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("sample-bom.csv");
    InputStream resourceAsStream = classLoader.getResourceAsStream("sample-bom.csv");
    assertThat(resourceAsStream).isNotNull();
    
    BomFile uut = BomFile.load(new InputStreamReader(resourceAsStream));

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

    assertThat(uut.getRows().size()).isEqualTo(16);

    assertThat(uut.getRows().get(0).get(0).toString()).isEqualTo("10k");
    assertThat(uut.getRows().get(0).get(0).getColumn().getType()).isEqualTo(Column.Type.Text);
    assertThat(uut.getRows().get(0).get(1).toString()).isEqualTo("R1, R2, R3, R4, R5, R6, R10, R11");
    assertThat(uut.getRows().get(0).get(1).getColumn().getType()).isEqualTo(Column.Type.Designator);
    assertThat(uut.getRows().get(0).get(2).toString()).isEqualTo("RESC1609X50X30NL10T20");
    assertThat(uut.getRows().get(0).get(2).getColumn().getType()).isEqualTo(Column.Type.Text);
    assertThat(uut.getRows().get(0).get(3).toString()).isEqualTo("C25804");
    assertThat(uut.getRows().get(0).get(3).getColumn().getType()).isEqualTo(Column.Type.Text);
  }

}