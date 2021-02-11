package com.example.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Location {
    @NotNull
    private double latitude;

    @NotNull
    private double longitude;
}
