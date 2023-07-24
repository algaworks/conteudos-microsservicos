package com.example.springamqp.aula1.infra.jackson;

import com.example.springamqp.aula1.core.JsonConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonConverterJacksonImpl implements JsonConverter {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String toJson(Object object) {
        try {
            return this.objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new JsonConverterException("Error writing to JSON", e);
        }
    }

}
