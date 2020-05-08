package com.bookshop.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
public class Category implements Serializable {

    protected long id;
    private String titleRu;
    private String titleEn;
    private List<Book> Books;

    @ManyToMany
    @JoinTable(name = "BOOK_WITH_CATEGORY",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))
    public List<Book> getBooks() {
        return Books;
    }

    public void setBooks(List<Book> books) {
        Books = books;
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

    public void setTitleRu(String titleRu) {
        this.titleRu = titleRu;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    @NotNull
    @Column(name = "TITLE_RU")
    public String getTitleRu() {
        return titleRu;
    }

    @NotNull
    @Column(name = "TITLE_EN")
    public String getTitleEn() {
        return titleEn;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    public Category(long id, String titleRu, String titleEn, List<Book> books) {
        this.id = id;
        this.titleRu = titleRu;
        this.titleEn = titleEn;
        Books = books;
    }

    public Category(String titleRu, String titleEn) {
        this.titleRu = titleRu;
        this.titleEn = titleEn;
    }

    public Category() {
    }

}
