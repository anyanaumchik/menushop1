package com.menushop.controller;

import com.menushop.service.CafeService;
import com.menushop.service.DishService;
import com.menushop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteController {

    @Autowired
    private DishService dishService;

    @Autowired
    private CafeService cafeService;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/dish/admin/delete/{id}")
    public String deleteDish(@PathVariable long id) {
        dishService.deleteById(id);
        return "redirect:/dish";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/cafe/admin/delete/{id}")
    public String deleteCafe(@PathVariable long id) {
        cafeService.deleteById(id);
        return "redirect:/cafe";
    }

}
