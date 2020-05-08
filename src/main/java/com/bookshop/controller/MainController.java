package com.bookshop.controller;


import com.bookshop.service.CafeService;
import com.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CafeService cafeService;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("books", bookService.getLastBooks());
        model.addAttribute("authors", cafeService.findAll());
        return "main";
    }

}
