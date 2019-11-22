package com.clough42.bom.panelizer.csv;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DesignatorTest {

  @Test
  public void testGoodParse() {
    Designator ret = Designator.parse("Q1");
    assertThat(ret).isNotNull();
    assertThat(ret.toString()).isEqualTo("Q1");
  }

  @Test
  public void testExtraspacesParse() {
    Designator ret = Designator.parse("   Q1  ");
    assertThat(ret).isNotNull();
    assertThat(ret.toString()).isEqualTo("Q1");
  }
  
  @Test
  public void testBaseOffset() {
    Designator d = Designator.parse("R23");
    Designator ret = d.offset(1000);
    
    assertThat(ret.toString()).isEqualTo("R1023");
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testBadParse() {
    Designator.parse("ABA");
  }
}