package com.example.mapper;

import com.example.dto.AddressCreateDto;
import com.example.dto.AddressDto;
import com.example.model.Address;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-02-11T18:43:24+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address toAddress(AddressCreateDto dto) {

        Address address = new Address();

        if ( dto != null ) {
            address.setAddress( dto.getAddress() );
            address.setCity( dto.getCity() );
            address.setState( dto.getState() );
            address.setCountry( dto.getCountry() );
            address.setLatitude( dto.getLatitude() );
            address.setLongitude( dto.getLongitude() );
        }

        return address;
    }

    @Override
    public AddressDto fromAddress(Address address) {

        AddressDto addressDto = new AddressDto();

        if ( address != null ) {
            addressDto.id = address.getId();
            addressDto.address = address.getAddress();
            addressDto.city = address.getCity();
            addressDto.state = address.getState();
            addressDto.country = address.getCountry();
            addressDto.latitude = address.getLatitude();
            addressDto.longitude = address.getLongitude();
        }

        return addressDto;
    }
}
