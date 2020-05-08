package com.bookshop.model.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "CAFE")
public class Cafe {

    private Long id;
    private String name;
    private List<Book> books;
    private AuthorImage image;

    @OneToOne
    @JoinColumn(name = "FK_IMAGE_ID")
    public AuthorImage getImage() {
        return image;
    }

    public void setImage(AuthorImage image) {
        this.image = image;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @OneToMany(targetEntity = Book.class, mappedBy = "cafe", orphanRemoval = true)
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cafe(Long id, String name, List<Book> books, AuthorImage image) {
        this.id = id;
        this.name = name;
        this.books = books;
        this.image = image;
    }

    public Cafe(String name) {
        this.name = name;
    }

    public Cafe() {

    }

}
