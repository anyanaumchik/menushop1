package com.bookshop.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "BOOK")
public class Book {

    private long id;
    private double price;
    private String titleRu;
    private String titleEn;
    private List<Category> categories;
    private Author author;
    private BookImage image;
    private String description;

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToOne
    @JoinColumn(name = "FK_IMAGE_ID")
    public BookImage getImage() {
        return image;
    }

    public void setImage(BookImage image) {
        this.image = image;
    }

    @ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_AUTHOR_ID")
    @Access(AccessType.PROPERTY)
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @ManyToMany
    @JoinTable(name = "BOOK_WITH_CATEGORY",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @NotNull
    @Column(name = "TITLE_RU")
    public String getTitleRu() {
        return titleRu;
    }

    public void setTitleRu(String titleRu) {
        this.titleRu = titleRu;
    }

    @NotNull
    @Column(name = "TITLE_EN")
    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public Book(long id, double price, String titleRu, String titleEn, List<Category> categories, Author author, BookImage image, String description) {
        this.id = id;
        this.price = price;
        this.titleRu = titleRu;
        this.titleEn = titleEn;
        this.categories = categories;
        this.author = author;
        this.image = image;
        this.description = description;
    }

    public Book(double price, String titleRu, String titleEn, String description) {
        this.price = price;
        this.titleRu = titleRu;
        this.titleEn = titleEn;
        this.description = description;
    }

    public Book() {
    }

}
