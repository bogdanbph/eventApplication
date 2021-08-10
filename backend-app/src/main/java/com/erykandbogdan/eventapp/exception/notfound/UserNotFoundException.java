package com.erykandbogdan.eventapp.exception.notfound;

import com.erykandbogdan.eventapp.exception.NotFoundException;

import java.math.BigDecimal;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(BigDecimal id) {
        super("user.not.found", "User with id " + id + " does not exist");
    }
}
