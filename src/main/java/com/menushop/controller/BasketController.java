package com.menushop.controller;

import com.menushop.model.entity.Dish;
import com.menushop.model.entity.CustomUserDetail;
import com.menushop.service.DishService;
import com.menushop.service.CartService;
import com.menushop.service.ServiceResponse;
import com.menushop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BasketController {

    @Autowired
    private CartService cartService;

    @Autowired
    private DishService dishService;

    @Autowired
    private UserService userService;


    @PostMapping("/saveDish")
    public ResponseEntity<Object> addDishToCart(@AuthenticationPrincipal CustomUserDetail user, @RequestBody Dish dish) {
        cartService.addSingleDishToBasket(user, dishService.findById(dish.getId()));
        ServiceResponse<Long> response = new ServiceResponse<>("success", dish.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("account/deleteFromBasket")
    public ResponseEntity<Object> deleteDish(@AuthenticationPrincipal CustomUserDetail user, @RequestBody Dish dish) {
        cartService.deleteDishFromBasket(user, dish);
        ServiceResponse<Long> response = new ServiceResponse<>("success", dish.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("account/dishToProcessing")
    public ResponseEntity<Object> dishToProcessing(@AuthenticationPrincipal CustomUserDetail user, @RequestBody Dish dish) {
        cartService.sendDishToProcessing(user, dish);
        ServiceResponse<Long> response = new ServiceResponse<Long>("success", dish.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("account/AllDishToProcessing")
    public ResponseEntity<Object> allDishToProcessing(@AuthenticationPrincipal CustomUserDetail user) {
        cartService.sendAllDishesToProcessing(user);
        ServiceResponse<Long> response = new ServiceResponse<Long>("success", user.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
