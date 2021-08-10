package com.erykandbogdan.eventapp.service;

import com.erykandbogdan.eventapp.exception.notfound.UserNotFoundException;
import com.erykandbogdan.eventapp.model.User;
import com.erykandbogdan.eventapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j

/* Add logs! */
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    public Optional<User> getUserByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName,lastName);
    }

    @Transactional
    public User saveOrUpdateUser(User user) {
        Optional<User> persistedUserOpt = userRepository.findByEmail(user.getEmail());

        persistedUserOpt.ifPresent(persistedUser -> {
            user.setId(persistedUser.getId());
            user.setCreateDateTIme(persistedUser.getCreateDateTIme());
            user.setUpdateDateTime(LocalDateTime.now());
        });
        return userRepository.save(user);
    }

    public Optional<User> findUserById(BigDecimal id) {
        return userRepository.findById(id);
    }

    public void deleteById(BigDecimal id) {

        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(id);
        }

        // add logic to see if the client still has events ongoing or planned in the close future
        userRepository.deleteById(id);
    }
}
