package com.sirlopu.msscbeerservice.web.mappers;

import com.sirlopu.msscbeerservice.domain.Beer;
import com.sirlopu.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
