package com.example.dto;

import javax.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShopSearchDto {

    private String shopName;

    @Valid
    private Location location;
}
