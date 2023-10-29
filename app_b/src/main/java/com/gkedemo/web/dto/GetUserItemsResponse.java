package com.gkedemo.web.dto;

import java.util.HashSet;
import java.util.Set;

public record GetUserItemsResponse(Set<ItemDto> items) {

    public static final String MEDIA_TYPE = "application/user.items.response.v1+json";

    public GetUserItemsResponse {
    }

    @Override
    public Set<ItemDto> items() {
        return new HashSet<>(items);
    }

}
