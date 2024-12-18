package com.example.employee.repository;

import com.example.employee.entity.User;
import com.example.employee.entity.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User save(UserDto userDto);
}
