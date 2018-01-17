package com.codecrocodile.club.common.util;

import java.io.IOException;

import com.codecrocodile.club.model.catchrecord.TimePeriod;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonTimePeriodSerializer extends JsonSerializer<TimePeriod> {

	@Override
	public void serialize(TimePeriod timePeriod, JsonGenerator gen, SerializerProvider provider) 
			throws IOException, JsonProcessingException {
		
		
		
		gen.writeString(timePeriod.getName());
	}
}
