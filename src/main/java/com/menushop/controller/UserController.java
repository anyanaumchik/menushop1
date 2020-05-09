package com.menushop.controller;

import com.menushop.model.entity.Dish;
import com.menushop.model.entity.User;
import com.menushop.service.CartService;
import com.menushop.service.RoleService;
import com.menushop.service.ServiceResponse;
import com.menushop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasAnyRole('ADMIN')")
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CartService cartService;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditInformation(@PathVariable Long user, Model model) {
        model.addAttribute("user", userService.findById(user));
        model.addAttribute("roles", roleService.findAll());
        return "userEdit";
    }

    @PostMapping
    public String userSaveEditByAdmin(@RequestParam("userId") User user,
                                      @RequestParam String role,
                                      @RequestParam String username,
                                      @RequestParam String password,
                                      @RequestParam String email) {
        userService.update(user, role, username, password, email);
        return "redirect:/user";
    }

    @GetMapping("{user}/cart")
    public String showUserBasket(@PathVariable Long user, Model model) {
        model.addAttribute("dishes", userService.findById(user).getCart().getDishes());
        model.addAttribute("dishesInProcessing", userService.findById(user).getCart().getDishesInProcessing());
        model.addAttribute("user", user);
        return "adminUsersBasket";
    }

    @PostMapping("{user}/approvedDish")
    public ResponseEntity<Object> approvedSingleDishToUser(@RequestBody Dish dish, @PathVariable User user) {
        cartService.approvedSingleDishesToUser(dish, user);
        ServiceResponse<Long> response = new ServiceResponse<Long>("success", dish.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
