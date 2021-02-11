package com.example.mapper;

import com.example.dto.AddressCreateDto;
import com.example.dto.AddressDto;
import com.example.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;
import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(uses = {DateMapper.class}, componentModel = "spring", unmappedTargetPolicy = ERROR, nullValueMappingStrategy = RETURN_DEFAULT)
public interface AddressMapper {

    @Mappings({
                      @Mapping(target = "id", ignore = true),
                      @Mapping(target = "createdDate", ignore = true),
                      @Mapping(target = "updateDate", ignore = true)
              })
    Address toAddress(AddressCreateDto dto);

    @Mappings({})
    AddressDto fromAddress(Address address);
}
