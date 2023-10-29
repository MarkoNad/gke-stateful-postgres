package com.gkedemo.web.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ItemDto(UUID id, @NotNull String name, @NotNull UUID ownerId) {

}
