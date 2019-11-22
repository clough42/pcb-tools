package com.clough42.bom.panelizer.csv;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DesignatorList extends ArrayList<Designator> {
  
  private static final String DESIGNATOR_LIST_REGEX = "^(([A-Za-z][0-9]+)(\\s*))+$";
  private static final Pattern regex = Pattern.compile(DESIGNATOR_LIST_REGEX);
  
  public static DesignatorList parse(String designators) {
    designators = designators.trim();
    Matcher m = regex.matcher(designators);
    if( ! m.matches() ) {
      throw new IllegalArgumentException("Designator list does not match regular expression: " + designators);
    }
    
    DesignatorList list = new DesignatorList();
    for( String designatorStr: designators.split("\\s+") ) {
      list.add(Designator.parse(designatorStr));
    }
    return list;
  }

  @Override
  public String toString() {
    String out = "";
    for(Designator designator: this) {
      if(out.length() > 0 ) {
        out = out + " ";
      }
      out = out + designator.toString();
    }
    
    return out;
  }

  public DesignatorList offset(int offset) {
    return this.stream()
      .map(x -> x.offset(offset))
      .collect(Collectors.toCollection(() -> new DesignatorList()));
    
  }
}
