package com.erykandbogdan.eventapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class IntroController {

    @GetMapping(value = "/home")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("hello");
    }

}
