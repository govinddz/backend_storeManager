package com.example.controller;

import com.example.controller.dto.PageDto;
import com.example.dto.AddressDto;
import com.example.dto.Location;
import com.example.dto.ShopDto;
import com.example.dto.ShopSearchDto;
import com.example.model.Shop;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.OK;

@Rollback
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ShopControllerTest {

    protected HttpHeaders headers;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createShop() {
        this.createShopDetails("test",26.8783675,75.7596791);
    }

    private ShopDto createShopDetails(String shopName, double lat, double lng) {
        ShopDto shopDto = this.prepareShopDetails(shopName,lat, lng);

        headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        ResponseEntity<ShopDto> exchange = restTemplate.exchange("/v2/shop", POST, new HttpEntity(shopDto, headers), ShopDto.class);
        assertEquals(OK, exchange.getStatusCode());
        assertNotNull(exchange.getBody());
        ShopDto response = exchange.getBody();
        assertEquals(shopDto.shopName, response.shopName);
        assertEquals(shopDto.ownerName, response.ownerName);
        assertEquals(shopDto.category, response.category);
        assertEquals(shopDto.address.city, response.address.city);
        assertEquals(shopDto.address.state, response.address.state);
        assertEquals(shopDto.address.country, response.address.country);
        return response;
    }

    private ShopDto prepareShopDetails(String name, double lat, double lng) {
        ShopDto shopDto = new ShopDto();
        shopDto.shopName = name;
        shopDto.category = Shop.ShopCategory.General_Store;
        shopDto.ownerName = RandomStringUtils.randomAlphabetic(10);
        AddressDto addressDto = new AddressDto();
        addressDto.address = RandomStringUtils.randomAlphabetic(15);
        addressDto.city = RandomStringUtils.randomAlphabetic(5);
        addressDto.state = RandomStringUtils.randomAlphabetic(7);
        addressDto.country = RandomStringUtils.randomAlphabetic(7);
        addressDto.latitude = lat;
        addressDto.longitude = lng;
        shopDto.address =  addressDto;
        return shopDto;
    }

    @Test
    public void getShopsByName() {
        this.createShopDetails("test",26.861668,75.7644593);
        this.createShopDetails("test demo",26.8783675,75.7596791);
        this.createShopDetails("demo",26.9252337,75.8010876);

        ShopSearchDto searchDto = new ShopSearchDto();
        searchDto.setShopName("te");

        ResponseEntity<PageDto<ShopDto>> exchange = restTemplate.exchange("/v2/shops", POST, new HttpEntity(searchDto, headers), new ParameterizedTypeReference<PageDto<ShopDto>>() {
        });
        assertNotNull(exchange.getBody());

        assertEquals(
                2,
                exchange
                        .getBody()
                        .getContent()
                        .size());

        ShopSearchDto locationDto = this.prepareShopSearchDto(26.8747617, 75.7590125);
        ResponseEntity<PageDto<ShopDto>> locationExchange = restTemplate.exchange("/v2/shops", POST, new HttpEntity(locationDto, headers), new ParameterizedTypeReference<PageDto<ShopDto>>() {});
        assertNotNull(locationExchange.getBody());
        assertEquals(
                2,
                locationExchange
                        .getBody()
                        .getContent()
                        .size());

        ShopSearchDto shopSearchDto = this.prepareShopSearchDto(26.9196878, 75.78800509999999);
        ResponseEntity<PageDto<ShopDto>> locationExchange1 = restTemplate.exchange("/v2/shops", POST, new HttpEntity(shopSearchDto, headers), new ParameterizedTypeReference<PageDto<ShopDto>>() {});
        assertNotNull(locationExchange1.getBody());
        assertEquals(
                1,
                locationExchange1
                        .getBody()
                        .getContent()
                        .size());
    }

    private ShopSearchDto prepareShopSearchDto(double lt, double lng)  {
        ShopSearchDto locationDto = new ShopSearchDto();
        Location location = new Location();
        location.setLatitude(lt);
        location.setLongitude(lng);
        locationDto.setLocation(location);
        return locationDto;
    }

}
