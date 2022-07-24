package com.example.eshop.service;

import com.example.eshop.domain.User;
import com.example.eshop.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {  //security
    boolean save(UserDto userDTO);
    void save(User user);
    List<UserDto> getAll();

    User findByName(String name);
    void updateProfile(UserDto userDto);

    boolean activateUser(String activateCode);
}
