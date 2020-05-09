package com.menushop.controller;

import com.menushop.service.DishService;
import com.menushop.service.CategoryService;
import com.menushop.service.FilterService;
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
    private DishService dishService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FilterService filterService;

    @GetMapping
    public String filter(Model model, @RequestParam(defaultValue = "") String filter) {
        if (filter != null && !filter.equals("")) {
            model.addAttribute("dishes", dishService.findByTitleEnOrTitleRu(filter, filter));
            model.addAttribute("cafes", filterService.cafesFilter(filter));
            model.addAttribute("category", categoryService.findFirstByTitleEnOrTitleRu(filter, filter));
        }
        model.addAttribute("filter", filter);
        return "filter";
    }


}
