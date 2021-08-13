package com.erykandbogdan.eventapp.exception.notfound;

import com.erykandbogdan.eventapp.exception.NotFoundException;

public class PageNotFoundException extends NotFoundException {
    public PageNotFoundException(Integer pageNumber) {
        super("page.not.found", "Page of interviews with id " + pageNumber + " does not exist");
    }
}
