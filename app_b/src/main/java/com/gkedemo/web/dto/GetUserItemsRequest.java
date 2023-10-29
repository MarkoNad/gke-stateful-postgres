package com.gkedemo.web.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GetUserItemsRequest(@NotNull UUID ownerId) {

    public static final String MEDIA_TYPE = "application/user.items.request.v1+json";

}
