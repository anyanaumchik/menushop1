package com.menushop.model.entity;

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
    private List<Dish> dishes;

    @ManyToMany
    @JoinTable(name = "DISH_WITH_CATEGORY",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "DISH_ID"))
    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
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

    public Category(long id, String titleRu, String titleEn, List<Dish> dishes) {
        this.id = id;
        this.titleRu = titleRu;
        this.titleEn = titleEn;
        this.dishes = dishes;
    }

    public Category(String titleRu, String titleEn) {
        this.titleRu = titleRu;
        this.titleEn = titleEn;
    }

    public Category() {
    }

}
