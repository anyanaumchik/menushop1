package com.bookshop.controller;

import com.bookshop.exception.PageNotFoundException;
import com.bookshop.model.entity.Cafe;
import com.bookshop.service.AuthorImageService;
import com.bookshop.service.AuthorService;
import com.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/cafe")
public class CafeController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorImageService authorImageService;

    @Autowired
    private BookService bookService;

    @GetMapping("admin/{cafeId}")
    public String cafeEditPage(@PathVariable("cafeId") Long cafe, Model model) {
        model.addAttribute("cafe", authorService.findById(cafe));
        return "cafeEdit";
    }

    @PostMapping("admin/{cafeId}")
    public String cafeSaveEditedInformation(@RequestParam String surname, @RequestParam String name, @RequestParam("cafeId") Cafe cafe,
                                            @RequestParam MultipartFile image) throws IOException {
        try {
            authorService.update(surname, name, cafe, image);
        } catch (Exception e) {
            throw new PageNotFoundException();
        }
        return "redirect:/cafe";
    }

    @GetMapping
    public String authorList(Model model) {
        model.addAttribute("cafes", authorService.findAll());
        return "cafeList";
    }

    @PostMapping
    public String addCafe(@RequestParam String surname,
                          @RequestParam String name,
                          @RequestParam MultipartFile image,
                          Model model) throws IOException {
        if (authorService.findBySurnameAndName(surname, name).isPresent()) {
            model.addAttribute("cafeError", "cafe is already exist");
            model.addAttribute("cafes", authorService.findAll());

            return "cafeList";
        }
        authorService.create(surname, name);
        authorImageService.add(image, authorService.findBySurnameAndName(surname, name).get());
        return "redirect:/cafe";
    }

    @GetMapping("/{cafe}/books")
    public String authorBooks(Model model, @PathVariable long cafe, @PageableDefault(size = 12) Pageable pageable) {
        try {
            model.addAttribute("page", bookService.findAllByAuthor(authorService.findById(cafe), pageable));
        } catch (Exception e) {
            throw new PageNotFoundException();
        }
        model.addAttribute("cafe", "");
        model.addAttribute("cafe", authorService.findById(cafe));
        model.addAttribute("url", "/cafe/" + cafe + "/books");
        return "bookList";
    }


}
