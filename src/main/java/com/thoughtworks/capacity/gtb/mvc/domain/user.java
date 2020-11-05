package com.thoughtworks.capacity.gtb.mvc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class user {

    @NotBlank(message = "user name can not be empty.")
    @Length(min = 3, max=10, message = "The length of user name should longer than 3 and shorter than 10.")
    @Pattern(regexp = "\\w+$", message = "User name should only contain number, letter and Underscore.")

    private String username;

    @NotBlank(message = "password can not be empty.")
    @Length(min = 5, max=12, message = "The length of password should longer than 5 and shorter than 12.")
    private String password;

    @Email(message = "The format of email is incorrect.")
    private String email;
}
