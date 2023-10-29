package com.gkedemo.web.mapper;

import com.gkedemo.domain.Item;
import com.gkedemo.web.dto.ItemDto;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ItemMapper {

    public ItemDto toDto(final Item item) {
        Assert.notNull(item, "The item cannot be null.");
        return new ItemDto(item.getId(), item.getName(), item.getOwnerId());
    }

    public Item toEntity(final ItemDto itemDto) {
        Assert.notNull(itemDto, "The item DTO cannot be null.");
        return new Item(itemDto.name(), itemDto.ownerId());
    }

}
