package com.bookshop.controller;

import com.bookshop.model.entity.Book;
import com.bookshop.model.entity.User;
import com.bookshop.service.CartService;
import com.bookshop.service.RoleService;
import com.bookshop.service.ServiceResponse;
import com.bookshop.service.UserService;
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
        model.addAttribute("books", userService.findById(user).getCart().getBooks());
        model.addAttribute("booksInProcessing", userService.findById(user).getCart().getBooksInProcessing());
        model.addAttribute("user", user);
        return "adminUsersBasket";
    }

    @PostMapping("{user}/approvedBook")
    public ResponseEntity<Object> approvedSingleBookToUser(@RequestBody Book book, @PathVariable User user) {
        cartService.approvedSingleBookToUser(book, user);
//        Basket basket = user.getBasket();
//        basket.getBooksInProcessing().remove(bookService.findById(book.getId()));
//        basket.getBooksApproved().add(bookService.findById(book.getId()));
//        basketService.save(basket);
        ServiceResponse<Long> response = new ServiceResponse<Long>("success", book.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
