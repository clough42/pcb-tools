package com.clough42.bom.panelizer.csv;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValueTest {

  @Test
  public void testGetIntValue() {
    Value uut = Value.parse("100mil", null);

    assertThat(uut.getIntValue()).isEqualTo(100);
    assertThat(uut.getDoubleValue()).isEqualTo(100.0);
    assertThat(uut.getUnit()).isEqualTo("mil");
  }

  @Test
  public void testFloatingPointValue() {
    Value uut = Value.parse("100.500mil", null);

    assertThat(uut.getDoubleValue()).isEqualTo(100.5);
    assertThat(uut.getUnit()).isEqualTo("mil");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFloatingPointNotAnInt() {
    Value uut = Value.parse("100.500mil", null);

    uut.getIntValue();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotANumber() {
    Value uut = Value.parse("10X.500mil", null);

    uut.getDoubleValue();
  }

  @Test
  public void testNoUnit() {
    Value uut = Value.parse("100.500", null);

    assertThat(uut.getUnit()).isNull();
  }

}