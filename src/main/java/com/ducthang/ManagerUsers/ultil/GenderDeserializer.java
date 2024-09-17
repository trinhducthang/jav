package com.ducthang.ManagerUsers.ultil;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class GenderDeserializer extends JsonDeserializer<Gender> {
    @Override
    public Gender deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String value = jsonParser.getText();
        if ("male".equalsIgnoreCase(value)) {
            return Gender.MALE;
        } else if ("female".equalsIgnoreCase(value)) {
            return Gender.FEMALE;
        } else {
            throw new IllegalArgumentException("Invalid value for Gender: " + value);
        }
    }
}
