package com.bookshop.controller;

import com.bookshop.model.entity.Book;
import com.bookshop.service.CafeService;
import com.bookshop.service.BookService;
import com.bookshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private CafeService cafeService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String bookList(Model model, /*@AuthenticationPrincipal CustomUserDetail user,*/
                           @PageableDefault(value = 12) Pageable pageable) {
        model.addAttribute("url", "/book");
        model.addAttribute("page", bookService.findAllPage(pageable));
        model.addAttribute("categories", categoryService.findAll());
        return "bookList";
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping/*("/admin/create")*/
    public String bookCreate(@RequestParam MultipartFile image,
                             @RequestParam Map<String, String> form,
                             Model model, @PageableDefault(value = 12) Pageable pageable) throws IOException {
        if (!cafeService.findByName( form.get("authorName").trim()).isPresent()) {
            model.addAttribute("url", "/book");
            model.addAttribute("page", bookService.findAllPage(pageable));
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("authorNotFoundError", "");
            return "bookList";
        }
        bookService.create(Double.parseDouble(form.get("price")), form.get("titleRu").trim(), form.get("titleEn").trim(), form.get("description").trim(), cafeService.findByName(form.get("authorName").trim()).get(), form, image);
        model.addAttribute("bookAddSuccess", "");
        model.addAttribute("url", "/book");
        model.addAttribute("page", bookService.findAllPage(pageable));
        model.addAttribute("categories", categoryService.findAll());
        return "bookList";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/admin/{book}")
    public String bookEdit(@PathVariable Long book, Model model) {
        Book byId = bookService.findById(book);
        if (bookIsNull(byId))
            return "redirect:/book";
        model.addAttribute("book", byId);
        model.addAttribute("categories", categoryService.findAll());
        return "bookEdit";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/admin/save")
    public String bookSaveEdit(
            @RequestParam MultipartFile image,
            @RequestParam Map<String, String> form,
            @RequestParam("bookId") Book book) throws IOException {
        if (!cafeService.findByName( form.get("authorName")).isPresent()) {
            return "redirect:/book/admin/" + book.getId();
        }
        bookService.update(book, Double.parseDouble(form.get("price")),
                form.get("titleEn"), form.get("titleRu"),
                form.get("authorName"), form.get("description"), form, image);
        return "redirect:/book";
    }

    @GetMapping("/{book}")
    public String book(@PathVariable Long book, Model model) {
        Book byId = bookService.findById(book);
        if (bookIsNull(byId)) return "redirect:/book";
        model.addAttribute("book", byId);
        model.addAttribute("categories", categoryService.findAll());
        return "book";
    }

    private boolean bookIsNull(Book byId) {
        return byId == null;
    }
}
