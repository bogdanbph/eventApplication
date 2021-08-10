package com.erykandbogdan.eventapp.auth;

import com.erykandbogdan.eventapp.model.User;
import com.erykandbogdan.eventapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Vector;

import static com.erykandbogdan.eventapp.security.ApplicationUserRole.USER;

@Repository
public class ApplicationUserDaoService implements ApplicationUserDao{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public ApplicationUserDaoService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<User> users = userRepository.findAll();
        List<ApplicationUser> applicationUsers = new Vector<>();
        for (var user: users) {
            ApplicationUser appUser = ApplicationUser.builder()
                    .username(user.getEmail())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .grantedAuthorities(USER.getGrantedAuthorities())
                    .isAccountNonExpired(true)
                    .isAccountNonLocked(true)
                    .isCredentialsNonExpired(true)
                    .isEnabled(true)
                    .build();

            applicationUsers.add(appUser);
        }

        return applicationUsers;
    }
}
