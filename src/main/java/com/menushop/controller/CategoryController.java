package com.menushop.controller;

import com.menushop.service.DishService;
import com.menushop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DishService dishService;

    @GetMapping
    public String allCategory(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categoryList";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/admin/create")
    public String createCategory(@RequestParam String titleEn,
                                 @RequestParam String titleRu) {
        categoryService.create(titleEn, titleRu);
        return "redirect:/category";
    }

    @GetMapping("/{category}")
    public String singleCategory(@PathVariable Long category, Model model, @PageableDefault(size = 12) Pageable pageable) {
        model.addAttribute("categoryPage", "");
        model.addAttribute("category", categoryService.findById(category));
        model.addAttribute("page", dishService.findAllByCategories(categoryService.findById(category), pageable));
        model.addAttribute("url", "/category/" + category);
        return "dishList";
    }


}
