package com.example.controller;

import javax.validation.Valid;
import com.example.dto.ShopDto;
import com.example.dto.ShopRequestCreateDto;
import com.example.dto.ShopSearchDto;
import com.example.helper.PageRequestBuilder;
import com.example.mapper.ShopMapper;
import com.example.service.ShopService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api
@RestController
@RequestMapping(value = "/v2")
public class ShopController {

    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private ShopService shopService;

    @CrossOrigin(originPatterns = "*")
    @PostMapping(value = {
            "/shop"
    }, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ShopDto> createShop(@Valid @RequestBody ShopRequestCreateDto dto) {
        return ResponseEntity.ok().body(shopMapper.fromShop(shopService.shopCreate(shopMapper.toShop(dto))));
    }

    @CrossOrigin(originPatterns = "*")
    @PostMapping(value = {
            "/shops"
    }, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ShopDto>> getShops(@Valid @RequestBody ShopSearchDto dto,
                                                  @RequestParam(required = false) Integer pageNumber,
                                                  @RequestParam(required = false) Integer pageSize) {
        PageRequest pageRequest = PageRequestBuilder.getPageRequest(pageSize, pageNumber, "-createdDate");
        return ResponseEntity.ok().body(shopService.getShops(dto, pageRequest).map(shop -> shopMapper.fromShop(shop)));
    }

}
