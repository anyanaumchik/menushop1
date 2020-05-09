package com.menushop.controller;

import com.menushop.model.entity.Dish;
import com.menushop.service.CafeService;
import com.menushop.service.DishService;
import com.menushop.service.CategoryService;
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
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private CafeService cafeService;

    @Autowired
    private DishService dishService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String dishList(Model model, /*@AuthenticationPrincipal CustomUserDetail user,*/
                           @PageableDefault(value = 12) Pageable pageable) {
        model.addAttribute("url", "/dish");
        model.addAttribute("page", dishService.findAllPage(pageable));
        model.addAttribute("categories", categoryService.findAll());
        return "dishList";
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping/*("/admin/create")*/
    public String dishCreate(@RequestParam MultipartFile image,
                             @RequestParam Map<String, String> form,
                             Model model, @PageableDefault(value = 12) Pageable pageable) throws IOException {
        if (!cafeService.findByName( form.get("cafeName").trim()).isPresent()) {
            model.addAttribute("url", "/dish");
            model.addAttribute("page", dishService.findAllPage(pageable));
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("cafeNotFoundError", "");
            return "dishList";
        }
        dishService.create(Double.parseDouble(form.get("price")), form.get("titleRu").trim(), form.get("titleEn").trim(), form.get("description").trim(), cafeService.findByName(form.get("cafeName").trim()).get(), form, image);
        model.addAttribute("dishAddSuccess", "");
        model.addAttribute("url", "/dish");
        model.addAttribute("page", dishService.findAllPage(pageable));
        model.addAttribute("categories", categoryService.findAll());
        return "dishList";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/admin/{dish}")
    public String dishEdit(@PathVariable Long dish, Model model) {
        Dish byId = dishService.findById(dish);
        if (dishIsNull(byId))
            return "redirect:/dish";
        model.addAttribute("dish", byId);
        model.addAttribute("categories", categoryService.findAll());
        return "dishEdit";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/admin/save")
    public String dishSaveEdit(
            @RequestParam MultipartFile image,
            @RequestParam Map<String, String> form,
            @RequestParam("dishId") Dish dish) throws IOException {
        if (!cafeService.findByName( form.get("cafeName")).isPresent()) {
            return "redirect:/dish/admin/" + dish.getId();
        }
        dishService.update(dish, Double.parseDouble(form.get("price")),
                form.get("titleEn"), form.get("titleRu"),
                form.get("cafeName"), form.get("description"), form, image);
        return "redirect:/dish";
    }

    @GetMapping("/{dish}")
    public String dish(@PathVariable Long dish, Model model) {
        Dish byId = dishService.findById(dish);
        if (dishIsNull(byId)) return "redirect:/dish";
        model.addAttribute("dish", byId);
        model.addAttribute("categories", categoryService.findAll());
        return "dish";
    }

    private boolean dishIsNull(Dish byId) {
        return byId == null;
    }
}
