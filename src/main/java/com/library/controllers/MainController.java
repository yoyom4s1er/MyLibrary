package com.library.controllers;

import com.library.models.Message;
import com.library.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/home")
public class MainController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping
    public String greeting(Model model) {
        Iterable<Message> messages = messageRepository.findAll();

        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping
    public String add(@RequestParam String text, String tag, Model model){
        Message message = new Message(text, tag);

        messageRepository.save(message);

        return "redirect:/home";
    }

    @GetMapping("/{filter}")
    public String filter(@RequestParam String filter, Model model) {
        List<Message> messages = messageRepository.findByTag(filter);

        model.addAttribute("messages", messages);

        return "main";
    }

}
