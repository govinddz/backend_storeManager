package com.example.mapper;

import com.example.dto.ShopDto;
import com.example.dto.ShopRequestCreateDto;
import com.example.model.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;
import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(uses = {AddressMapper.class}, componentModel = "spring", unmappedTargetPolicy = ERROR, nullValueMappingStrategy = RETURN_DEFAULT)
public interface ShopMapper {

    @Mappings({
                      @Mapping(target = "id", ignore = true),
                      @Mapping(target = "createdDate", ignore = true),
                      @Mapping(target = "updateDate", ignore = true)
              })
    Shop toShop(ShopRequestCreateDto dto);

    @Mappings({})
    ShopDto fromShop(Shop shop);
}
