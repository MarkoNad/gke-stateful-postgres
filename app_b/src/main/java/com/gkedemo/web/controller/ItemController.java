package com.gkedemo.web.controller;

import com.gkedemo.domain.Item;
import com.gkedemo.domain.ItemRepository;
import com.gkedemo.web.dto.GetUserItemsRequest;
import com.gkedemo.web.dto.GetUserItemsResponse;
import com.gkedemo.web.dto.ItemDto;
import com.gkedemo.web.mapper.ItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
public class ItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    private final ItemMapper itemMapper;

    private final ItemRepository itemRepository;

    public ItemController(ItemMapper itemMapper, ItemRepository itemRepository) {
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
    }

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@RequestBody final ItemDto itemDto) {
        LOGGER.info("Create item called: {}", itemDto);
        final Item item = itemMapper.toEntity(itemDto);
        final Item savedItem = itemRepository.save(item);
        final ItemDto savedItemDto = itemMapper.toDto(savedItem);
        LOGGER.info("Saved item: {}", savedItemDto);
        return ResponseEntity.ok(savedItemDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItem(@PathVariable("id") final UUID itemId) {
        LOGGER.info("Get item called for ID: {}", itemId);
        final Optional<Item> maybeItem = itemRepository.findById(itemId);
        if (maybeItem.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        final ItemDto itemDto = itemMapper.toDto(maybeItem.get());
        LOGGER.info("Found item: {}", itemDto);
        return ResponseEntity.ok(itemDto);
    }

    @PostMapping(consumes = GetUserItemsRequest.MEDIA_TYPE, produces = GetUserItemsResponse.MEDIA_TYPE)
    public ResponseEntity<GetUserItemsResponse> getUserItems(@RequestBody final GetUserItemsRequest request) {
        LOGGER.info("Get items called for user: {}", request);
        final List<Item> items = itemRepository.findByOwnerId(request.ownerId());
        LOGGER.info("Found items: {}", items);
        final Set<ItemDto> itemDtos = items.stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toSet());
        final GetUserItemsResponse response = new GetUserItemsResponse(itemDtos);
        return ResponseEntity.ok(response);
    }

}
