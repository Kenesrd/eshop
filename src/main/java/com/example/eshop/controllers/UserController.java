package com.example.eshop.controllers;

import com.example.eshop.domain.User;
import com.example.eshop.dto.UserDto;
import com.example.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userService.getAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/new")
    public String newUser(Model model){
        System.out.println("Called Method newUser()");
        model.addAttribute("user", new UserDto());
        return "user";
    }

    @PostAuthorize("isAuthenticated() and #username == authentication.principal.username")
    @GetMapping("/{name}/roles")
    @ResponseBody
    public String getRoles(@PathVariable("name") String username){
        System.out.println("Called Method getRoles()");
        User byName = userService.findByName(username);
        return byName.getRole().name(); 
    }

    @PostMapping("/new")
    public String saveUser(UserDto userDTO, Model model){
        if (userService.save(userDTO)){
            return "redirect:/users";
        } else {
            model.addAttribute("user", userDTO);
        }
        return "user";
    }

    @GetMapping("/profile")
    public String profileUser(Model model, Principal principal){
        if (principal == null){
            throw new RuntimeException("You are not authorize");
        }
        User user = userService.findByName(principal.getName());

        UserDto userDto = UserDto.builder()
                .username(user.getName())
                .email(user.getEmail())
                .build();

        model.addAttribute("user", userDto);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfileUser(UserDto userDto, Principal principal, Model model){
        if (principal == null || !Objects.equals(principal.getName(), userDto.getUsername())){
            throw new RuntimeException("You are not Authorize");
        }
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()
                                         && !Objects.equals(userDto.getPassword(),
                                            userDto.getMatchingPassword())){
            model.addAttribute("user", userDto);

            // Нужно добавить какое то сообщение, но сделаем это в другой раз
            return "profile";
        }
        userService.updateProfile(userDto);

        return "redirect:/users/profile";
    }

}
