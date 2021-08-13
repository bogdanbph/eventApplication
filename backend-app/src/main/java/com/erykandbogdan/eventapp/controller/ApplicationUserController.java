package com.erykandbogdan.eventapp.controller;

import com.erykandbogdan.eventapp.model.ApplicationUser;
import com.erykandbogdan.eventapp.service.ApplicationUserService;
import com.erykandbogdan.eventapp.util.PageContent;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/appuser")
@AllArgsConstructor
public class ApplicationUserController {

    private final ApplicationUserService applicationUserService;

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

    @GetMapping
    public ResponseEntity<PageContent> findAll(@RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
        if (pageNumber == null) {
            return ResponseEntity.ok(applicationUserService.findAll());
        }
        return ResponseEntity.ok(applicationUserService.findAll(pageNumber, ApplicationUserService.PAGE_SIZE));
    }
}
