package com.sirlopu.msscbeerservice.services;

import com.sirlopu.msscbeerservice.web.model.BeerDto;
import com.sirlopu.msscbeerservice.web.model.BeerPagedList;
import com.sirlopu.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest);

    BeerDto getBeerById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeerById(UUID beerId, BeerDto beerDto);
}
