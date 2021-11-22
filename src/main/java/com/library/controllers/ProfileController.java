package com.library.controllers;

import com.library.models.Book;
import com.library.models.User;
import com.library.repository.BookRepository;
import com.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/profile")
public class ProfileController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }


    @GetMapping("/{username}")
    public String openProfile(@PathVariable String username, Model model) throws UsernameNotFoundException {

        model.addAttribute("username", username);
        return "profile";
    };

    @GetMapping("/repository")
    public String openRepository(Model model) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(getCurrentUsername()).get();

        model.addAttribute("books", user.getBooks());

        model.addAttribute("username", getCurrentUsername());
        return "yourRepository";
    };
}
