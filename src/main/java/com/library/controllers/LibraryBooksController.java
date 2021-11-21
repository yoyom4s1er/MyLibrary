package com.library.controllers;

import com.library.models.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Controller
public class LibraryBooksController {

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

        return "library-main";
    }

    @GetMapping("/librarybooks/add")
    public String bookAdd(Model model) {
        return "library-add";
    }

    @PostMapping("/librarybooks/add")
    public String bookAdd(@RequestParam String name, String time_to_export, Model model) {
        Book book = new Book(name, time_to_export);
        bookRepository.save(book);
        return "redirect:/librarybooks";
    }
}
