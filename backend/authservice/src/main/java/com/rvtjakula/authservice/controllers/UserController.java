package com.rvtjakula.authservice.controllers;

import com.rvtjakula.authservice.dtos.SignUpRequestDto;
import com.rvtjakula.authservice.dtos.UserDto;
import com.rvtjakula.authservice.models.User;
import com.rvtjakula.authservice.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserDto signUp(@RequestBody SignUpRequestDto requestDto) {
        User user = userService.signUp(
                requestDto.getName(),
                requestDto.getEmail(),
                requestDto.getPassword()
        );

        return UserDto.from(user);
    }


}
