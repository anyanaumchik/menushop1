package com.bookshop.controller;

import com.bookshop.service.BookService;
import com.bookshop.service.CategoryService;
import com.bookshop.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/filter")
public class FilterController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FilterService filterService;

    @GetMapping
    public String filter(Model model, @RequestParam(defaultValue = "") String filter) {
        if (filter != null && !filter.equals("")) {
            model.addAttribute("books", bookService.findByTitleEnOrTitleRu(filter, filter));
            model.addAttribute("authors", filterService.authorsFilter(filter));
            model.addAttribute("category", categoryService.findFirstByTitleEnOrTitleRu(filter, filter));
        }
        model.addAttribute("filter", filter);
        return "filter";
    }


}
