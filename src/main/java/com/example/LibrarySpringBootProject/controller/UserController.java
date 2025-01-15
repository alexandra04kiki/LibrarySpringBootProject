package com.example.LibrarySpringBootProject.controller;

import com.example.LibrarySpringBootProject.dto.UserDto;
import com.example.LibrarySpringBootProject.model.Book;
import com.example.LibrarySpringBootProject.model.RegistrationRequest;
import com.example.LibrarySpringBootProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public String getUsers(Model model){
        List<UserDto> userDtos = userService.getAllUsers();
        model.addAttribute("title", "Users");
        model.addAttribute("users", userDtos);
        model.addAttribute("user", new RegistrationRequest());
        return "users";
    }

    @PostMapping("/createUserAsAdmin")
    public String createUser(@ModelAttribute("user") RegistrationRequest registrationRequest){
        userService.registerUser(registrationRequest);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }


    @PostMapping("/actionUser")
    public String deleteOrUpdateUser(@RequestParam(required = false) List<String> selectedUsers,
                                     @RequestParam(required = false) String updateUser,
                                     @RequestParam(required = false) String newEmailAddress) {

        if(selectedUsers != null){
            for(String selectedUser : selectedUsers){
                userService.deleteUserByUsername(selectedUser);
            }
        }

        if(updateUser != null){
            if(newEmailAddress != null){
                userService.updateUserEmail(updateUser, newEmailAddress);
            }
        }

        return "redirect:/users";
    }
}

