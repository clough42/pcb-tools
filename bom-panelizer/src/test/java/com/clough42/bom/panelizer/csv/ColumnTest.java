package com.clough42.bom.panelizer.csv;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ColumnTest {

  @Test
  public void testParseText() {
    Column uut = Column.parse("Package");

    assertThat(uut.getName()).isEqualTo("Package");
    assertThat(uut.getType()).isEqualTo(Column.Type.Text);
  }

  @Test
  public void testParseDesignator() {
    assertThat(Column.parse("Designator").getType()).isEqualTo(Column.Type.Designator);
    assertThat(Column.parse("designator").getType()).isEqualTo(Column.Type.Designator);
    assertThat(Column.parse("DESIGNATOR").getType()).isEqualTo(Column.Type.Designator);
  }

  @Test
  public void testParseXCoordinate() {
    assertThat(Column.parse("Mid X").getType()).isEqualTo(Column.Type.XCoordinate);
    assertThat(Column.parse("Ref X").getType()).isEqualTo(Column.Type.XCoordinate);
    assertThat(Column.parse("Pad X").getType()).isEqualTo(Column.Type.XCoordinate);
    assertThat(Column.parse("mid x").getType()).isEqualTo(Column.Type.XCoordinate);
    assertThat(Column.parse("ref x").getType()).isEqualTo(Column.Type.XCoordinate);
    assertThat(Column.parse("pad x").getType()).isEqualTo(Column.Type.XCoordinate);
  }

  @Test
  public void testParseYCoordinate() {
    assertThat(Column.parse("Mid Y").getType()).isEqualTo(Column.Type.YCoordinate);
    assertThat(Column.parse("Ref Y").getType()).isEqualTo(Column.Type.YCoordinate);
    assertThat(Column.parse("Pad Y").getType()).isEqualTo(Column.Type.YCoordinate);
    assertThat(Column.parse("mid y").getType()).isEqualTo(Column.Type.YCoordinate);
    assertThat(Column.parse("ref y").getType()).isEqualTo(Column.Type.YCoordinate);
    assertThat(Column.parse("pad y").getType()).isEqualTo(Column.Type.YCoordinate);
  }

}