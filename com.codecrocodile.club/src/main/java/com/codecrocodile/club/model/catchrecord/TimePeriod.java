package com.codecrocodile.club.model.catchrecord;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TimePeriod {
	UNKNOWN (1, "Unknown"),
	MORNING (2, "Morning"),
	AFTERNOON (3, "Afternoon"),
	EVENING (4, "Evening"),
	NIGHT (5, "Night");
	
    private Integer id;
    private String name;
 
    private TimePeriod(final Integer id, final String name) {
        this.id = id;
        this.name = name;
    }
	
    @JsonValue
    public String getName() {
        return this.name;
    }
    
    @JsonCreator
    public static TimePeriod create(String val) {
    	TimePeriod[] states = TimePeriod.values();
      for (TimePeriod state : states) {
        if (state.getName().equalsIgnoreCase(val)) {
          return state;
        }
      }
      return UNKNOWN;
    }
	
}
