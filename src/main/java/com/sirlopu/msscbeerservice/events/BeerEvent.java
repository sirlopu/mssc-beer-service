package com.sirlopu.msscbeerservice.events;

import com.sirlopu.msscbeerservice.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -2727789411801784586L;

    private final BeerDto beerDto;
}
