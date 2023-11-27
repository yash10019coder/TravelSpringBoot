package com.kotiswar.travel.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FeedbackDto {

    private Long id;
    @NotBlank
    private Long tripId;
    @NotBlank
    private Long userId;
    @NotBlank
    private Integer rating;
    @NotBlank
    @Size(max = 500)
    private String comments;
}
