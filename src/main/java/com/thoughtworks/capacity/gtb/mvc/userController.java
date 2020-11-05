package com.thoughtworks.capacity.gtb.mvc;

import com.thoughtworks.capacity.gtb.mvc.Exception.UsernameExistException;
import com.thoughtworks.capacity.gtb.mvc.Exception.UsernameNotExistException;
import com.thoughtworks.capacity.gtb.mvc.Exception.WrongUsernameOrPasswordException;
import com.thoughtworks.capacity.gtb.mvc.domain.user;
import com.thoughtworks.capacity.gtb.mvc.domain.userResponse;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@RestController
@Validated
public class userController {
    private final UserService userService;

    public userController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void userRegister(@RequestBody @Valid user user) throws UsernameExistException {
        userService.registerUser(user);
        return;
    }

    @GetMapping("/login")
    public ResponseEntity<userResponse> logUser(@RequestParam("username")
                                                    @NotBlank(message = "user name can not be empty.")
                                                    @Length(min = 3, max=10, message = "The length of user name should longer than 3 and shorter than 10.")
                                                    @Pattern(regexp = "\\w+$", message = "User name should only contain number, letter and Underscore.")
                                                            String username,
                                                @NotBlank(message = "password can not be empty.")
                                                @Length(min = 5, max=12, message = "The length of password should longer than 5 and shorter than 12.")
                                                @RequestParam("password") String password) throws WrongUsernameOrPasswordException, UsernameNotExistException {
        userResponse userResponse = userService.logUser(username, password);
        return ResponseEntity.ok(userResponse);

    }
}
