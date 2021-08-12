package com.erykandbogdan.eventapp.service;

import com.erykandbogdan.eventapp.exception.notfound.UserNotFoundException;
import com.erykandbogdan.eventapp.model.ConfirmationToken;
import com.erykandbogdan.eventapp.model.ApplicationUser;
import com.erykandbogdan.eventapp.repository.ApplicationUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ApplicationUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final ApplicationUserRepository appUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    public ApplicationUserService(ApplicationUserRepository appUserRepository, BCryptPasswordEncoder passwordEncoder, ConfirmationTokenService confirmationTokenService) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.confirmationTokenService = confirmationTokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(ApplicationUser appUser) {
        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = passwordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

    public List<ApplicationUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    public ApplicationUser findUserById(Long id) {
        Optional<ApplicationUser> tempAppUser = appUserRepository.findById(id);

        if (tempAppUser.isEmpty()) {
            throw new UserNotFoundException(id);
        }

        return tempAppUser.get();
    }

    public void deleteUserById(Long id) {
        Optional<ApplicationUser> tempAppUser = appUserRepository.findById(id);
        if (tempAppUser.isEmpty()) {
            log.warn("The user with id {} not found", id);
            throw new UserNotFoundException(id);
        }

        // TODO: implement logic for events still ongoing or planned in close future

        appUserRepository.deleteById(id);
        log.debug("deleteById() was completed successfully");
    }

    public ApplicationUser saveOrUpdateUser(ApplicationUser applicationUser) {
        Optional<ApplicationUser> tempAppUser = appUserRepository.findByEmail(applicationUser.getEmail());
        tempAppUser.ifPresent(tempUser -> {
            applicationUser.setId(tempUser.getId());
            applicationUser.setCreateDateTIme(tempUser.getCreateDateTIme());
            applicationUser.setUpdateDateTime(LocalDateTime.now());
            applicationUser.setPassword(passwordEncoder.encode(applicationUser.getPassword()));
        });
        return appUserRepository.save(applicationUser);
    }

    public Optional<ApplicationUser> findUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }
}
