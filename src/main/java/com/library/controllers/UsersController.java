package com.library.controllers;

import com.library.models.Role;
import com.library.models.Status;
import com.library.models.User;
import com.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;


@Controller
public class UsersController {

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ResponseEntity<Optional<User>> getUsersByName(String name) {
        return new ResponseEntity<Optional<User>>(userRepository.findByUsername(name), HttpStatus.OK);
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String registration(@RequestParam String username, String password, String confirmPassword, Model model) {

        if (password.equals(confirmPassword)) {

            String hashedPassword = passwordEncoder.encode(password);

            User newUser = new User(username, hashedPassword);

            newUser.setRole(Role.USER);
            newUser.setStatus(Status.ACTIVE);

            userRepository.save(newUser);

            return "redirect:/login";
        }

        model.addAttribute("message", "Пароли не совпадают");
        return "sign-up";
    }
}
