package com.example.web_ban_cong.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank(message = "name cannot be blank")
    private String name;

    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @NotBlank(message = "Email number is required")
    @Email(message = "Please provide a valid email address")
    private String mail;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}
