package com.example.demo.payload.request;

import lombok.Data;

import java.util.Set;

import javax.validation.constraints.*;

@Data
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 3, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 20)
    private String lastName;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}
