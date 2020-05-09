package com.menushop.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "DISH")
public class Dish {

    private long id;
    private double price;
    private String description;
    private List<Category> categories;
    private Cafe cafe;
    private DishImage image;
    private String titleRu;
    private String titleEn;

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToOne
    @JoinColumn(name = "FK_IMAGE_ID")
    public DishImage getImage() {
        return image;
    }

    public void setImage(DishImage image) {
        this.image = image;
    }

    @ManyToOne(targetEntity = Cafe.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_CAFE_ID")
    @Access(AccessType.PROPERTY)
    public Cafe getCafe() {
        return cafe;
    }

    public void setCafe(Cafe cafe) {
        this.cafe = cafe;
    }

    @ManyToMany
    @JoinTable(name = "DISH_WITH_CATEGORY",
            joinColumns = @JoinColumn(name = "DISH_ID"),
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

    public Dish(long id, double price, String titleRu, String titleEn, List<Category> categories, Cafe cafe, DishImage image, String description) {
        this.id = id;
        this.price = price;
        this.titleRu = titleRu;
        this.titleEn = titleEn;
        this.categories = categories;
        this.cafe = cafe;
        this.image = image;
        this.description = description;
    }

    public Dish(double price, String titleRu, String titleEn, String description) {
        this.price = price;
        this.titleRu = titleRu;
        this.titleEn = titleEn;
        this.description = description;
    }

    public Dish() {
    }

}
