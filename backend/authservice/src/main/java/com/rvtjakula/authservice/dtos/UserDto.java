package com.rvtjakula.authservice.dtos;

import com.rvtjakula.authservice.models.Role;
import com.rvtjakula.authservice.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    private List<String> roles;

    public static UserDto from(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());

        userDto.setRoles(new ArrayList<>());

        for (Role role : user.getRoles()) {
            userDto.getRoles().add(role.getValue());
        }

        return userDto;
    }
}
