package com.kotiswar.travel.payloads;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DriverDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String licenseNumber;
    @NotBlank
    private String contactInfo;
}
