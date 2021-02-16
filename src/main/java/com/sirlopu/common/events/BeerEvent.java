package com.sirlopu.common.events;

import com.sirlopu.msscbeerservice.web.model.BeerDto;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -2727789411801784586L;

    private BeerDto beerDto;
}
