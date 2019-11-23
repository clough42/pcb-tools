package com.clough42.bom.panelizer.csv;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class DesignatorList extends ArrayList<Designator> {

  public static DesignatorList parse(String designators) {
    designators = designators.trim();

    DesignatorList list = new DesignatorList();
    for (String designatorStr : designators.split("(\\s*)(\\s|,)(\\s*)")) {
      list.add(Designator.parse(designatorStr));
    }
    return list;
  }

  @Override
  public String toString() {
    StringBuilder out = new StringBuilder();

    for (Designator designator : this) {
      if (out.length() > 0) {
        out.append(", ");
      }
      out.append(designator.toString());
    }

    return out.toString();
  }

  public DesignatorList offset(int offset) {
    return this.stream()
      .map(x -> x.offset(offset))
      .collect(Collectors.toCollection(DesignatorList::new));

  }
}
