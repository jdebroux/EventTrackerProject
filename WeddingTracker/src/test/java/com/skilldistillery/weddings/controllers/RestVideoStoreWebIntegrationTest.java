package com.skilldistillery.weddings.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skilldistillery.weddings.entities.Wedding;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
public class RestVideoStoreWebIntegrationTest {
  private String baseUrl = "http://localhost:8084/";

  @Test
  public void testListAllFilms() throws IOException{
    // configure TestRestTemplate to get our film index route
    TestRestTemplate restTest = new TestRestTemplate();
    ResponseEntity<String> response = restTest.getForEntity(baseUrl + "api/weddings", String.class);

    assertThat( response.getStatusCode(), equalTo(HttpStatus.OK));

    ObjectMapper mapper = new ObjectMapper();

    // map to a JsonNode
    JsonNode responseJson = mapper.readTree(response.getBody());

    // map to a Collection of Film objects
    List<Wedding> weddings = mapper.readValue(response.getBody(),  mapper.getTypeFactory().constructCollectionType(List.class, Wedding.class));

    assertThat( responseJson.isMissingNode(), is(false));
    assertThat( weddings.get(0).getVenue().getId(), is(1));
  }
}