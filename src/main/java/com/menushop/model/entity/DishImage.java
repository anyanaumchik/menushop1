package com.menushop.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "DISH_IMAGE")
public class DishImage {
    private Long id;
    private String dishImage;


    @NotNull
    @Column(name = "IMAGE")
    public String getDishImage() {
        return dishImage;
    }

    public void setDishImage(String dishImage) {
        this.dishImage = dishImage;
    }


    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DishImage(Long id, String dishImage) {
        this.id = id;
        this.dishImage = dishImage;
    }

    public DishImage(String dishImag) {
        this.dishImage = dishImage;
    }

    public DishImage() {
    }

}
