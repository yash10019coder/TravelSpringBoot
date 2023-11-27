package com.kotiswar.travel.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    @NotBlank
    private String username;
    @Email
    private String email;
    @NotBlank
    private String passwordHash;
    @NotBlank
    private String role;
}
