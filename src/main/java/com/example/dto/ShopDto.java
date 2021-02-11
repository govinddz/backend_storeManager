package com.example.dto;

import java.io.Serializable;
import com.example.model.Shop;

public class ShopDto implements Serializable {

    public Long id;
    public String shopName;
    public String ownerName;
    public Shop.ShopCategory category;
    public AddressDto address;

}
