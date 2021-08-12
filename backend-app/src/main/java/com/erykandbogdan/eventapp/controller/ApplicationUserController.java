package com.erykandbogdan.eventapp.controller;

import com.erykandbogdan.eventapp.model.ApplicationUser;
import com.erykandbogdan.eventapp.service.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/appuser")
@AllArgsConstructor
public class ApplicationUserController {

    private final ApplicationUserService applicationUserService;

    @GetMapping
    public ResponseEntity<List<ApplicationUser>> getUsers() {
        return ResponseEntity.ok(applicationUserService.getAllUsers());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ApplicationUser> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(applicationUserService.findUserById(id));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        applicationUserService.deleteUserById(id);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Long> saveOrUpdateUser(@RequestBody @Valid ApplicationUser applicationUser) {
        applicationUserService.saveOrUpdateUser(applicationUser);
        return ResponseEntity.ok(applicationUser.getId());
    }
}
