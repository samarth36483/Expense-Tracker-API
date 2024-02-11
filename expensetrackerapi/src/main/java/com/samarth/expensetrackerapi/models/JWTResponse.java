package com.samarth.expensetrackerapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JWTResponse {

    private final String jwtToken;
}