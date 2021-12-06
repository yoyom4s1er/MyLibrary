package com.library.controllers;

import com.library.models.Book;
import com.library.models.User;
import com.library.repository.BookRepository;
import com.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.swing.text.View;
import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class LibraryBooksController {

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @ModelAttribute(value = "book")
    public Book getBook()
    {
        return new Book();
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/librarybooks")
    public String libraryMain(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        List<Book> bookList = new ArrayList<>();
        books.forEach(bookList::add);

        List<Book> bookListReverse = new ArrayList<>();

        for (int i= bookList.size()-1; i>=0; i--)
        {
            bookListReverse.add(bookList.get(i));
        }

        model.addAttribute("books", bookListReverse);
        model.addAttribute("username", getCurrentUsername());

        return "library-main";
    }

    @PostMapping("/{id}")
    public String bookSubscribe(@PathVariable Long id, Model model) {
        User user = userRepository.findByUsername(getCurrentUsername()).get();

        Book book = bookRepository.findById(id).get();

        user.subscribe(book);

        userRepository.save(user);

        return "redirect:/librarybooks";
    }

    @GetMapping("/librarybooks/add")
    public String bookAdd(Model model) {

        model.addAttribute("username", getCurrentUsername());
        return "library-add";
    }

    @PostMapping("/librarybooks/add")
    public String bookAdd(@RequestParam String name, String time_to_export, Model model) {
        Book book = new Book(name, time_to_export);
        bookRepository.save(book);
        return "redirect:/librarybooks";
    }

}
