package com.erykandbogdan.eventapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements BaseDto{

    @Size(max = 100)
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @Size(max = 100)
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    @Size(max = 20)
    @NotEmpty(message = "Phone number cannot be null")
    private String phoneNumber;

    @Size(max = 100)
    @NotEmpty(message = "Email cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;


    @Size(max = 100)
    @NotEmpty(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
    private String password;
}
