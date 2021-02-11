package com.example.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.example.model.Shop;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShopRequestCreateDto {

    @NotBlank
    private String shopName;

    @NotBlank
    public String ownerName;

    @NotNull
    public Shop.ShopCategory category;

    @NotNull
    @Valid
    public AddressCreateDto address;

}
