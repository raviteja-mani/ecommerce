package com.rvtjakula.authservice.services;

import com.rvtjakula.authservice.models.User;

public interface UserService {
    User signUp(String name, String email, String password);
}
