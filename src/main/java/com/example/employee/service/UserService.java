package com.example.employee.service;

import com.example.employee.entity.User;
import com.example.employee.entity.UserDto;

public interface UserService {
    User findByUsername(String username);

    User save(UserDto userDto);
}
