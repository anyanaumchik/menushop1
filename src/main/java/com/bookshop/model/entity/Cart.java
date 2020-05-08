package com.bookshop.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "BASKET")
public class Cart {

    private Long id;
    private List<Book> books;
    private List<Book> booksInProcessing;
    private List<Book> booksApproved;
    private User user;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToMany
    @JoinTable(name = "BOOK_WITH_BASKET",
            joinColumns = @JoinColumn(name = "BASKET_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_User_ID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }


    @ManyToMany
    @JoinTable(name = "BOOK_IN_PROCESSING_WITH_BASKET",
            joinColumns = @JoinColumn(name = "BASKET_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))
    public List<Book> getBooksInProcessing() {
        return booksInProcessing;
    }

    public void setBooksInProcessing(List<Book> booksInProcessing) {
        this.booksInProcessing = booksInProcessing;
    }

    @ManyToMany
    @JoinTable(name = "BOOK_APPROVED_WITH_BASKET",
            joinColumns = @JoinColumn(name = "BASKET_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))
    public List<Book> getBooksApproved() {
        return booksApproved;
    }

    public void setBooksApproved(List<Book> booksApproved) {
        this.booksApproved = booksApproved;
    }

    public Cart(Long id, List<Book> books, List<Book> booksInProcessing, List<Book> booksApproved, User user) {
        this.id = id;
        this.books = books;
        this.booksInProcessing = booksInProcessing;
        this.booksApproved = booksApproved;
        this.user = user;
    }

    public Cart(User user) {
        setUser(user);
    }

    public Cart() {
    }

    public Cart(Long id, List<Book> books, List<Book> booksInProcessing, List<Book> booksApproved) {
        this.id = id;
        this.books = books;
        this.booksInProcessing = booksInProcessing;
        this.booksApproved = booksApproved;
    }
}
