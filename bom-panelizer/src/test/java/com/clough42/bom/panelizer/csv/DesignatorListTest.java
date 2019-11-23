package com.clough42.bom.panelizer.csv;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class DesignatorListTest {

  @Test
  public void testHappyDay() {
    DesignatorList uut = DesignatorList.parse(" R1  Q2 L3   ");
    assertThat(uut.toString()).isEqualTo("R1, Q2, L3");
  }

  @Test
  public void testHappyDayCommas() {
    DesignatorList uut = DesignatorList.parse("  R1, R2, R3 , R4,  R5, R6, R10, R11");
    assertThat(uut.toString()).isEqualTo("R1, R2, R3, R4, R5, R6, R10, R11");
  }

  @Test
  public void testHappyDayMixed() {
    DesignatorList uut = DesignatorList.parse(" R1,Q2 L3   ");
    assertThat(uut.toString()).isEqualTo("R1, Q2, L3");
  }

  @Test
  public void testOffset() {
    DesignatorList uut = DesignatorList.parse("R1 Q2 L3");
    DesignatorList ret = uut.offset(3000);
    assertThat(ret.toString()).isEqualTo("R3001, Q3002, L3003");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadList() {
    DesignatorList.parse("R1R2R3");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadList2() {
    DesignatorList.parse("R1,R2R3");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadList3() {
    DesignatorList.parse("R1,, R2");
  }

}