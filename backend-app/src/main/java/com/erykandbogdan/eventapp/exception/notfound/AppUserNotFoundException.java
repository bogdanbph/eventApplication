package com.erykandbogdan.eventapp.exception.notfound;

import com.erykandbogdan.eventapp.exception.NotFoundException;

import java.math.BigDecimal;

public class AppUserNotFoundException extends NotFoundException {
    public AppUserNotFoundException(Long id) {
        super("user.not.found", "User with id " + id + " does not exist");
    }
}
