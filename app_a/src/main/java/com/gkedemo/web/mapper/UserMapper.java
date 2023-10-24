package com.gkedemo.web.mapper;

import com.gkedemo.domain.User;
import com.gkedemo.web.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class UserMapper {

    public UserDto toDto(final User user) {
        Assert.notNull(user, "The user cannot be null.");
        return new UserDto(user.getId(), user.getName());
    }

    public User toEntity(final UserDto userDto) {
        Assert.notNull(userDto, "The user DTO cannot be null.");
        return new User(userDto.name());
    }

}
