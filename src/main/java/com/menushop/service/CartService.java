package com.menushop.service;

import com.menushop.model.dataService.CartDataService;
import com.menushop.model.entity.Dish;
import com.menushop.model.entity.Cart;
import com.menushop.model.entity.CustomUserDetail;
import com.menushop.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartDataService cartDataService;

    @Autowired
    private UserService userService;

    @Autowired
    private DishService dishService;

    public List<Cart> findAll() {
        return cartDataService.findAll();
    }

    public Cart findById(long id) {
        return cartDataService.findById(id);
    }

    public void save(Cart cart) {
        cartDataService.save(cart);
    }

    public Cart create(User user) {
        User currentUser = userService.getCurrentUser(user);
        Cart cart = new Cart(currentUser);
        save(cart);
        currentUser.setCart(cart);
        userService.save(currentUser);
        return cart;
    }

    public void addSingleDishToBasket(User user, Dish dish) {
        Cart cart = userService.getCurrentUser(user).getCart();
        if (cart == null) {
            cart = create(user);
        }
        List<Dish> dishes = cart.getDishes();
        if (cart.getDishes() == null) {
            dishes = new ArrayList<>();
        }
        dishes.add(dish);
        save(cart);
    }

    public void deleteDishFromBasket(CustomUserDetail user, Dish dish) {
        Cart cart = userService.getCurrentUser(user).getCart();
        cart.getDishes().remove(dishService.findById(dish.getId()));
        save(cart);
    }

    public void sendDishToProcessing(CustomUserDetail user, Dish dish) {
        Cart cart = userService.getCurrentUser(user).getCart();
        cart.getDishes().remove(dishService.findById(dish.getId()));
        cart.getDishesInProcessing().add(dishService.findById(dish.getId()));
        save(cart);
    }

    public void sendAllDishesToProcessing(CustomUserDetail user) {
        Cart cart = userService.getCurrentUser(user).getCart();
        cart.getDishesInProcessing().addAll(cart.getDishes());
        cart.getDishes().clear();
        save(cart);
    }

    public void approvedSingleDishesToUser(Dish dish, User user) {
        Cart cart = userService.getCurrentUser(user).getCart();
        cart.getDishesInProcessing().remove(dishService.findById(dish.getId()));
        cart.getDishesApproved().add(dishService.findById(dish.getId()));
        save(cart);
    }
}
