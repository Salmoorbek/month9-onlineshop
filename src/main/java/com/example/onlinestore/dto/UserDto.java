package com.example.onlinestore.dto;

import lombok.*;
import javax.validation.constraints.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotBlank
    private String name;

    @NotBlank
    private String userName;

    @NotBlank
    @Email
    private String email;
}
