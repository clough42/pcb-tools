package com.clough42.bom.panelizer.csv;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class DesignatorListTest {
  
  @Test
  public void testHappyDay() {
    DesignatorList uut = DesignatorList.parse(" R1  Q2 L3   ");
    assertThat(uut.toString()).isEqualTo("R1 Q2 L3");
  }
  
  @Test
  public void testOffset() {
    DesignatorList uut = DesignatorList.parse("R1 Q2 L3");
    DesignatorList ret = uut.offset(3000);
    assertThat(ret.toString()).isEqualTo("R3001 Q3002 L3003");
  }

}