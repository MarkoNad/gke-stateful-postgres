package com.gkedemo.service.dto;

import org.springframework.http.MediaType;

import java.util.HashSet;
import java.util.Set;

public record GetUserItemsResponse(Set<ItemDto> items) {

    public static final String MEDIA_TYPE_STRING = "application/user.items.response.v1+json";

    public static final MediaType MEDIA_TYPE = new MediaType("application", "user.items.response.v1+json");

    public GetUserItemsResponse {
    }

    @Override
    public Set<ItemDto> items() {
        return new HashSet<>(items);
    }

}
