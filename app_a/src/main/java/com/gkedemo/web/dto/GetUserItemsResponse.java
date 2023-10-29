package com.gkedemo.web.dto;

import java.util.HashSet;
import java.util.Set;

public record GetUserItemsResponse(Set<ItemDto> items) {

    public GetUserItemsResponse(Set<ItemDto> items) {
        this.items = new HashSet<>(items);
    }

    @Override
    public Set<ItemDto> items() {
        return new HashSet<>(items);
    }

}
