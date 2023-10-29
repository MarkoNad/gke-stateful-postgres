package com.gkedemo.web.controller;

import com.gkedemo.domain.User;
import com.gkedemo.domain.UserRepository;
import com.gkedemo.service.UserService;
import com.gkedemo.web.dto.GetUserItemsResponse;
import com.gkedemo.web.dto.ItemDto;
import com.gkedemo.web.dto.UserDto;
import com.gkedemo.web.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final UserService userService;

    public UserController(UserMapper userMapper, UserRepository userRepository, UserService userService) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody final UserDto userDto) {
        LOGGER.info("Create user called: {}", userDto);
        final User user = userMapper.toEntity(userDto);
        final User savedUser = userRepository.save(user);
        final UserDto savedUserDto = userMapper.toDto(savedUser);
        LOGGER.info("Saved user: {}", savedUserDto);
        return ResponseEntity.ok(savedUserDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") final UUID userId) {
        LOGGER.info("Get user called for ID: {}", userId);
        final Optional<User> maybeUser = userRepository.findById(userId);
        if (maybeUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        final UserDto userDto = userMapper.toDto(maybeUser.get());
        LOGGER.info("Found user: {}", userDto);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<GetUserItemsResponse> getUserItems(@PathVariable("id") final UUID userId) {
        LOGGER.info("Get user items called for ID: {}", userId);
        final Set<String> items = userService.getUserItems(userId);
        final GetUserItemsResponse response = new GetUserItemsResponse(items.stream().map(ItemDto::new)
                .collect(Collectors.toSet()));
        LOGGER.info("Response: {}", response);
        return ResponseEntity.ok(response);
    }

}
