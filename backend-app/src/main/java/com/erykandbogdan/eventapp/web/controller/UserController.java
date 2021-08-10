package com.erykandbogdan.eventapp.web.controller;

import com.erykandbogdan.eventapp.model.User;
import com.erykandbogdan.eventapp.service.UserService;
import com.erykandbogdan.eventapp.web.dto.UserDto;
import com.erykandbogdan.eventapp.web.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userMapper.convertEntities(userService.getAllUsers()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") BigDecimal id) {
        return ResponseEntity.ok(userMapper.convert(userService.findUserById(id)));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable("id") BigDecimal id) {
        userService.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<BigDecimal> saveOrUpdateUser(@RequestBody @Valid UserDto userDto) {
        User user = userMapper.convert(userDto);
        userService.saveOrUpdateUser(user);
        return ResponseEntity.ok(user.getId());
    }
}
