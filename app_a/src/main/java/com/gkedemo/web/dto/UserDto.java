package com.gkedemo.web.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserDto(UUID id, @NotNull String name) {

}
