package com.sirlopu.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sirlopu.msscbeerservice.web.model.BeerDto;
import com.sirlopu.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//Need this import to get restdocs to work
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "dev.sirlopu", uriPort = 80)
@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getBeerById() throws Exception {

        ConstrainedFields fields = new ConstrainedFields(BeerDto.class);

        mockMvc.perform(get("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
                .param("iscold", "yes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/beer-get",
                        pathParameters(
                            parameterWithName("beerId").description("UUID of desired beer to get.")
                        ),
                        requestParameters(
                                parameterWithName("iscold").description("Is Beer Cold Query param")
                        ),
                        responseFields(
                                fields.withPath("beerId").description("Id of Beer").type(UUID.class),
                                fields.withPath("version").description("Version number").type(Integer.class.getTypeName()),
                                fields.withPath("createdDate").description("Date Created").type(OffsetDateTime.class),
                                fields.withPath("lastModifiedDate").description("Date Updated").type(OffsetDateTime.class),
                                fields.withPath("myLocalDate").description("Local Date").type(OffsetDateTime.class),
                                fields.withPath("beerName").description("Beer Name").type(String.class.getTypeName()),
                                fields.withPath("beerStyle").description("Beer Style").type(Enum.class.getTypeName()),
                                fields.withPath("upc").description("UPC of Beer").type(Long.class.getTypeName()),
                                fields.withPath("price").description("Price").type(BigDecimal.class.getTypeName()),
                                fields.withPath("quantityOnHand").description("Quantity On hand").type(Integer.class.getTypeName())
                        )
                ));
    }

    @Test
    void saveNewBeer() throws Exception {

        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        ConstrainedFields fields = new ConstrainedFields(BeerDto.class);

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated())
                .andDo(document("v1/beer-post",
                    requestFields(
                            fields.withPath("beerId").ignored(),
                            fields.withPath("version").ignored(),
                            fields.withPath("createdDate").ignored(),
                            fields.withPath("lastModifiedDate").ignored(),
                            fields.withPath("myLocalDate").ignored(),
                            fields.withPath("beerName").description("Name of the beer"),
                            fields.withPath("beerStyle").description("Style of Beer"),
                            fields.withPath("upc").description("Beer UPC").attributes(),
                            fields.withPath("price").description("Beer Price"),
                            fields.withPath("quantityOnHand").ignored()
                    )));
    }

    @Test
    void updateBeerById() throws Exception {

        ConstrainedFields fields = new ConstrainedFields(BeerDto.class);

        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent())
                .andDo(document("v1/beer-put",
                        requestFields(
                                fields.withPath("beerId").description("Id for Beer to update").type(UUID.class),
                                fields.withPath("version").ignored(),
                                fields.withPath("createdDate").ignored(),
                                fields.withPath("lastModifiedDate").ignored(),
                                fields.withPath("myLocalDate").ignored(),
                                fields.withPath("beerName").description("Name of the beer"),
                                fields.withPath("beerStyle").description("Style of Beer"),
                                fields.withPath("upc").description("Beer UPC").attributes(),
                                fields.withPath("price").description("Beer Price"),
                                fields.withPath("quantityOnHand").ignored()

                        )
                ));

    }

    BeerDto getValidBeerDto(){
        return BeerDto.builder()
                .beerName("My Beer")
                .beerStyle(BeerStyleEnum.ALE)
                .price(new BigDecimal("2.99"))
                .upc(1234123132421L)
                .build();
    }

    private static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ". ")));
        }
    }
}