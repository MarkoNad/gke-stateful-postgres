package com.gkedemo.service.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;

import java.util.UUID;

public record GetUserItemsRequest(@NotNull UUID ownerId) {

    public static final String MEDIA_TYPE_STRING = "application/user.items.request.v1+json";

    public static final MediaType MEDIA_TYPE = new MediaType("application", "user.items.request.v1+json");

}
