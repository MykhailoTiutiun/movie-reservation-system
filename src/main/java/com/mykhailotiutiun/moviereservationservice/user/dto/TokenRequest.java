package com.mykhailotiutiun.moviereservationservice.user.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TokenRequest (@NotNull @NotBlank String email,
                            @NotNull @NotBlank String password){}
