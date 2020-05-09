package com.menushop.model.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "CAFE")
public class Cafe {

    private Long id;
    private String name;
    private List<Dish> dishes;
    private CafeImage image;

    @OneToOne
    @JoinColumn(name = "FK_IMAGE_ID")
    public CafeImage getImage() {
        return image;
    }

    public void setImage(CafeImage image) {
        this.image = image;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @OneToMany(targetEntity = Dish.class, mappedBy = "cafe", orphanRemoval = true)
    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
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

    public Cafe(Long id, String name, List<Dish> dishes, CafeImage image) {
        this.id = id;
        this.name = name;
        this.dishes = dishes;
        this.image = image;
    }

    public Cafe(String name) {
        this.name = name;
    }

    public Cafe() {

    }

}
