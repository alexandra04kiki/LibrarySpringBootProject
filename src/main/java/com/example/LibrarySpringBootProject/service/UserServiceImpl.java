package com.example.LibrarySpringBootProject.service;

import com.example.LibrarySpringBootProject.dto.UserDto;
import com.example.LibrarySpringBootProject.mapper.RoleMapper;
import com.example.LibrarySpringBootProject.mapper.UserMapper;
import com.example.LibrarySpringBootProject.model.RegistrationRequest;
import com.example.LibrarySpringBootProject.model.User;
import com.example.LibrarySpringBootProject.repository.RoleRepository;
import com.example.LibrarySpringBootProject.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    @Override
    public boolean checkEmail(String email) {
        return userRepository.existsByEmailAddress(email);
    }

    @Override
    public UserDto registerUser(RegistrationRequest registrationRequest) {
        User user = User.builder()
                .username(registrationRequest.getUsername())
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .password(registrationRequest.getPassword())
                .emailAddress(registrationRequest.getEmailAddress())
                .role((roleRepository.findByRole("USER")))
                .build();




        return this.createUser(user);
    }

    public UserDto getLoginUser(){
        return userMapper.userEntityToDto(userRepository.findLoginUser().orElse(null));
    }

    public UserDto getUserById(Integer id){
        return userMapper.userEntityToDto(userRepository.findById(id).orElse(null));
    }

    public List<UserDto> getAllUsers(){
        return userMapper.userListEntityToDto(userRepository.findAll());
    }

    public UserDto createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.userEntityToDto(userRepository.save(user));
    }

    public UserDto updateUser(User user){
        return userMapper.userEntityToDto(userRepository.save(user));
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    @Override
    public void deleteUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        user.ifPresent(userRepository::delete);
    }

    @Override
    public void updateUserEmail(String username, String newEmailAddress) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            User userEntity = user.get();
            userEntity.setEmailAddress(newEmailAddress);
            userRepository.save(userEntity);
        }
    }
}