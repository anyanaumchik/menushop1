package com.bookshop.service;

import com.bookshop.model.dataService.CartDataService;
import com.bookshop.model.entity.Book;
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

    public void addSingleBookToBasket(User user, Book book) {
        Cart cart = userService.getCurrentUser(user).getCart();
        if (cart == null) {
            cart = create(user);
        }
        List<Book> books = cart.getBooks();
        if (cart.getBooks() == null) {
            books = new ArrayList<>();
        }
        books.add(book);
        save(cart);
    }

    public void deleteBookFromBasket(CustomUserDetail user, Book book) {
        Cart cart = userService.getCurrentUser(user).getCart();
        cart.getBooks().remove(bookService.findById(book.getId()));
        save(cart);
    }

    public void sendBookToProcessing(CustomUserDetail user, Book book) {
        Cart cart = userService.getCurrentUser(user).getCart();
        cart.getBooks().remove(bookService.findById(book.getId()));
        cart.getBooksInProcessing().add(bookService.findById(book.getId()));
        save(cart);
    }

    public void sendAllBooksToProcessing(CustomUserDetail user) {
        Cart cart = userService.getCurrentUser(user).getCart();
        cart.getBooksInProcessing().addAll(cart.getBooks());
        cart.getBooks().clear();
        save(cart);
    }

    public void approvedSingleBookToUser(Book book, User user) {
        Cart cart = userService.getCurrentUser(user).getCart();
        cart.getBooksInProcessing().remove(bookService.findById(book.getId()));
        cart.getBooksApproved().add(bookService.findById(book.getId()));
        save(cart);
    }
}
