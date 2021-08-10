package com.erykandbogdan.eventapp.web.controller.register;

import com.erykandbogdan.eventapp.model.User;
import com.erykandbogdan.eventapp.service.RegisterService;
import com.erykandbogdan.eventapp.service.UserService;
import com.erykandbogdan.eventapp.web.dto.UserDto;
import com.erykandbogdan.eventapp.web.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RegisterController {

    private final UserMapper userMapper;
    private final UserService userService;
    private final RegisterService registerService;

    @PostMapping(value = "/register")
    public ResponseEntity<BigDecimal> registerUser(@RequestBody @Valid UserDto userDto) {
        User user = userMapper.convert(userDto);
        userService.saveOrUpdateUser(user);
        return ResponseEntity.ok(user.getId());
    }
}
