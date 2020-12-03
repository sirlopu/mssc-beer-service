package com.sirlopu.msscbeerservice.services.inventory;

import com.sirlopu.msscbeerservice.bootstrap.BeerLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@Disabled // utility for manual testing
@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {

    private final String BEER_1_UUID = "0a818933-087d-47f2-ad83-2f986ed087eb";

    @Autowired
    BeerInventoryService beerInventoryService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getOnhandInventory() {
        //Integer qoh = beerInventoryService.getOnhandInventory(BeerLoader.BEER_1_UUID);
        Integer qoh = beerInventoryService.getOnhandInventory(UUID.fromString(BEER_1_UUID));

        System.out.println(qoh);

    }
}
