package com.erykandbogdan.eventapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
/*import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;*/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements BaseDto{

    private BigDecimal id;

    private String firstName;
    private String lastName;

    private String phoneNumber;
    private String email;
}
