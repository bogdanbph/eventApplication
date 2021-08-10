package com.erykandbogdan.eventapp.web.mapper;

import com.erykandbogdan.eventapp.model.User;
import com.erykandbogdan.eventapp.web.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements BaseMapper<UserDto, User> {
    @Override
    public UserDto convert(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .build();
    }

    @Override
    public User convert(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .build();
    }
}
