package com.example.LibrarySpringBootProject.service;


import com.example.LibrarySpringBootProject.dto.UserDto;
import com.example.LibrarySpringBootProject.model.RegistrationRequest;
import com.example.LibrarySpringBootProject.model.User;

import java.util.List;

public interface UserService {

    boolean checkEmail(String email);

    UserDto registerUser(RegistrationRequest registrationRequest);

    UserDto getLoginUser();

    UserDto getUserById(Integer id);

    List<UserDto> getAllUsers();

    UserDto createUser(User user);

    UserDto updateUser(User user);

    void deleteUser(User user);

    void deleteUserByUsername(String username);

    void updateUserEmail(String username, String newEmailAddress);
}