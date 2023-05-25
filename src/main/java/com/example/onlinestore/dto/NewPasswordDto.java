package com.example.onlinestore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewPasswordDto {
    @NotEmpty(message = "Password can not be empty")
    private String currentPassword;

    @NotBlank
    @Size(min = 8, max = 24)
    private String newPassword;
}
