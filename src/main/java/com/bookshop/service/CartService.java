package com.bookshop.service;

import com.bookshop.model.dataService.CartDataService;
import com.bookshop.model.entity.Dish;
import com.bookshop.model.entity.Cart;
import com.bookshop.model.entity.CustomUserDetail;
import com.bookshop.model.entity.User;
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
    private BookService bookService;

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

    public void addSingleBookToBasket(User user, Dish dish) {
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

    public void deleteBookFromBasket(CustomUserDetail user, Dish dish) {
        Cart cart = userService.getCurrentUser(user).getCart();
        cart.getDishes().remove(bookService.findById(dish.getId()));
        save(cart);
    }

    public void sendBookToProcessing(CustomUserDetail user, Dish dish) {
        Cart cart = userService.getCurrentUser(user).getCart();
        cart.getDishes().remove(bookService.findById(dish.getId()));
        cart.getDishesInProcessing().add(bookService.findById(dish.getId()));
        save(cart);
    }

    public void sendAllBooksToProcessing(CustomUserDetail user) {
        Cart cart = userService.getCurrentUser(user).getCart();
        cart.getDishesInProcessing().addAll(cart.getDishes());
        cart.getDishes().clear();
        save(cart);
    }

    public void approvedSingleBookToUser(Dish dish, User user) {
        Cart cart = userService.getCurrentUser(user).getCart();
        cart.getDishesInProcessing().remove(bookService.findById(dish.getId()));
        cart.getDishesApproved().add(bookService.findById(dish.getId()));
        save(cart);
    }
}
