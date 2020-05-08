package com.bookshop.controller;

import com.bookshop.exception.PageNotFoundException;
import com.bookshop.model.entity.Cafe;
import com.bookshop.service.CafeImageService;
import com.bookshop.service.CafeService;
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
    private CafeService cafeService;

    @Autowired
    private CafeImageService cafeImageService;

    @Autowired
    private BookService bookService;

    @GetMapping("admin/{cafeId}")
    public String cafeEditPage(@PathVariable("cafeId") Long cafe, Model model) {
        model.addAttribute("cafe", cafeService.findById(cafe));
        return "cafeEdit";
    }

    @PostMapping("admin/{cafeId}")
    public String cafeSaveEditedInformation(@RequestParam String surname, @RequestParam String name, @RequestParam("cafeId") Cafe cafe,
                                            @RequestParam MultipartFile image) throws IOException {
        try {
            cafeService.update(surname, name, cafe, image);
        } catch (Exception e) {
            throw new PageNotFoundException();
        }
        return "redirect:/cafe";
    }

    @GetMapping
    public String authorList(Model model) {
        model.addAttribute("cafes", cafeService.findAll());
        return "cafeList";
    }

    @PostMapping
    public String addCafe(@RequestParam String name,
                          @RequestParam MultipartFile image,
                          Model model) throws IOException {
        if (cafeService.findByName(name).isPresent()) {
            model.addAttribute("cafeError", "cafe is already exist");
            model.addAttribute("cafes", cafeService.findAll());

            return "cafeList";
        }
        cafeService.create(name);
        cafeImageService.add(image, cafeService.findByName(name).get());
        return "redirect:/cafe";
    }

    @GetMapping("/{cafe}/dishes")
    public String authorBooks(Model model, @PathVariable long cafe, @PageableDefault(size = 12) Pageable pageable) {
        try {
            model.addAttribute("page", bookService.findAllByAuthor(cafeService.findById(cafe), pageable));
        } catch (Exception e) {
            throw new PageNotFoundException();
        }
        model.addAttribute("cafe", "");
        model.addAttribute("cafe", cafeService.findById(cafe));
        model.addAttribute("url", "/cafe/" + cafe + "/books");
        return "dishList";
    }


}
