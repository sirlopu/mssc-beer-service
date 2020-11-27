package com.sirlopu.msscbeerservice.web.model;

import com.sirlopu.msscbeerservice.bootstrap.BeerLoader;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public class BaseTest {

    BeerDto getDto(){
        return BeerDto.builder()
                .beerName("BeerName")
                .beerStyle(BeerStyleEnum.ALE)
                .id(UUID.randomUUID())
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .price(new BigDecimal("12.99"))
                .upc(BeerLoader.BEER_3_UPC)
                .myLocalDate(LocalDate.now())
                .build();
                
    }
}
