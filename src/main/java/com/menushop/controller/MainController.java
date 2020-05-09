package com.menushop.controller;


import com.menushop.service.CafeService;
import com.menushop.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private DishService dishService;

    @Autowired
    private CafeService cafeService;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("dishes", dishService.getLastDishes());
        model.addAttribute("cafes", cafeService.findAll());
        return "main";
    }

}
