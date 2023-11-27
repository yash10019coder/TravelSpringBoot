package com.kotiswar.travel.payloads;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class TripDto {
    private Long id;
    @NotBlank
    private Long driverId;
    @NotBlank
    private Long vehicleId;
    @NotBlank
    private Date startTime;
    private Date endTime;
}
