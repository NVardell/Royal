package com.stated.royal.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kong.unirest.GenericType;
import kong.unirest.ObjectMapper;
import kong.unirest.UnirestException;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.TimeZone;

/**
 * Custom Object Mapper
 *
 * Used by UniRest to map json responses to my models
 *
 * @author Nate Vardell
 * @since 8/10/2019
 */
@Log4j2
public class CustomObjectMapper implements ObjectMapper {

    private final com.fasterxml.jackson.databind.ObjectMapper om;

    CustomObjectMapper() {
        this(JsonMapper.builder().defaultTimeZone(TimeZone.getDefault()).addModule(new JavaTimeModule()).build());
        om.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private CustomObjectMapper(com.fasterxml.jackson.databind.ObjectMapper om){
        this.om = om;
    }

    public <T> T readValue(String value, Class<T> valueType) {
        try {
            return om.readValue(value, valueType);
        } catch (IOException e) {
            throw new UnirestException(e);
        }
    }

    public <T> T readValue(String value, GenericType<T> genericType) {
        try {
            return om.readValue(value,  om.constructType(genericType.getType()));
        } catch (IOException e) {
            throw new UnirestException(e);
        }
    }

    public String writeValue(Object value) {
        try {
            return om.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("JSON Parse Exception in Custom Mapper = {}", e.getOriginalMessage());
            throw new UnirestException(e);
        }
    }
}
