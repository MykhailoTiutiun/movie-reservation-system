package com.mykhailotiutiun.moviereservationservice.user.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TokenRequest (@NotNull @NotBlank @Size(min = 5, max = 100) @Email String email,
                            @NotNull @NotBlank @Size(min = 6, max = 100) String password){}
