package com.bookshop.controller;

import com.bookshop.service.AuthorService;
import com.bookshop.service.BookService;
import com.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/book/admin/delete/{id}")
    public String deleteBook(@PathVariable long id) {
        bookService.deleteById(id);
        return "redirect:/book";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/author/admin/delete/{id}")
    public String deleteAuthor(@PathVariable long id) {
        authorService.deleteById(id);
        return "redirect:/author";
    }

}
