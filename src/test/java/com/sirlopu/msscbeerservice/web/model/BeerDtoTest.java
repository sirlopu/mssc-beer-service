package com.sirlopu.msscbeerservice.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@JsonTest
class BeerDtoTest extends BaseTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testSerializedDto() throws JsonProcessingException {

        BeerDto beerDto = getDto();

        String jsonString = objectMapper.writeValueAsString(beerDto);

        System.out.println(jsonString);
    }

    @Test
    void testDeserializedDto() throws IOException {

        String jsonString = "{\"version\":null," +
                "\"createdDate\":\"2020-11-26T17:02:10-0800\"," +
                "\"lastModifiedDate\":\"2020-11-26T17:02:10-0800\"," +
                "\"beerName\":\"BeerName\",\"beerStyle\":\"ALE\"," +
                "\"upc\":123123412341232,\"price\":\"12.99\"," +
                "\"quantityOnHand\":null,\"myLocalDate\":\"20201126\"," +
                "\"beerId\":\"d2d15246-fcdb-403c-bcc8-22e919fc5119\"}\n";

        BeerDto beerDto = objectMapper.readValue(jsonString, BeerDto.class);

        System.out.println(beerDto);
    }
}