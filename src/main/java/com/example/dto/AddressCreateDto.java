package com.example.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressCreateDto {

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @NotBlank
    public String state;

    @NotBlank
    public String country;

    @NotNull
    public double latitude;

    @NotNull
    public double longitude;

}
