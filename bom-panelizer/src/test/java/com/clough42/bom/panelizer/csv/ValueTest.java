package com.clough42.bom.panelizer.csv;

import com.clough42.bom.panelizer.panelizer.BoardInstance;
import com.clough42.bom.panelizer.panelizer.PanelDescription;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ValueTest {

  @Mock
  private Column mockColumn;

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

  @Test
  public void testGetDesignatorList() {
    Value uut = Value.parse("R1 R2 R3", null);

    DesignatorList ret = uut.getDesignatorList();

    assertThat(ret.toString()).isEqualTo("R1, R2, R3");
  }

  @Test
  public void testOffsetBomDesignators() {
    Value uut = Value.parse("R1 R2 R3", mockColumn);

    PanelDescription panel = new PanelDescription();
    panel.add(new BoardInstance(1000, 2000, 3000));
    panel.add(new BoardInstance(4000, 5000, 6000));

    Value ret = uut.offsetBomDesignators(panel);

    assertThat(ret.getColumn()).isSameAs(mockColumn);
    assertThat(ret.toString()).isEqualTo("R1001, R1002, R1003, R4001, R4002, R4003");
  }

  @Test
  public void testOffsetBomDesignator() {
    Value uut = Value.parse("R1", mockColumn);

    Value ret = uut.offsetBomDesignator(new BoardInstance(1000, 2000, 3000));

    assertThat(ret.getColumn()).isSameAs(mockColumn);
    assertThat(ret.toString()).isEqualTo("R1001");
  }

  @Test
  public void testOffsetValue() {
    Value uut = Value.parse("1000.5mil", mockColumn);

    Value ret = uut.offsetValue(3000.0d);

    assertThat(ret.getColumn()).isSameAs(mockColumn);
    assertThat(ret.toString()).isEqualTo("4000.500mil");
  }

}