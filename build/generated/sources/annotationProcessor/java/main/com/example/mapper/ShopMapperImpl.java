package com.example.mapper;

import com.example.dto.ShopDto;
import com.example.dto.ShopRequestCreateDto;
import com.example.model.Shop;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-02-11T18:43:24+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class ShopMapperImpl implements ShopMapper {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public Shop toShop(ShopRequestCreateDto dto) {

        Shop shop = new Shop();

        if ( dto != null ) {
            shop.setShopName( dto.getShopName() );
            shop.setOwnerName( dto.getOwnerName() );
            shop.setCategory( dto.getCategory() );
            shop.setAddress( addressMapper.toAddress( dto.getAddress() ) );
        }

        return shop;
    }

    @Override
    public ShopDto fromShop(Shop shop) {

        ShopDto shopDto = new ShopDto();

        if ( shop != null ) {
            shopDto.id = shop.getId();
            shopDto.shopName = shop.getShopName();
            shopDto.ownerName = shop.getOwnerName();
            shopDto.category = shop.getCategory();
            shopDto.address = addressMapper.fromAddress( shop.getAddress() );
        }

        return shopDto;
    }
}
