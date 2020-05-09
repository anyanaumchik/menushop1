package com.menushop.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "BASKET")
public class Cart {

    private Long id;
    private List<Dish> dishes;
    private List<Dish> dishesInProcessing;
    private List<Dish> dishesApproved;
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
    @JoinTable(name = "DISH_WITH_BASKET",
            joinColumns = @JoinColumn(name = "BASKET_ID"),
            inverseJoinColumns = @JoinColumn(name = "DISH_ID"))
    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
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
    @JoinTable(name = "DISH_IN_PROCESSING_WITH_BASKET",
            joinColumns = @JoinColumn(name = "BASKET_ID"),
            inverseJoinColumns = @JoinColumn(name = "DISH_ID"))
    public List<Dish> getDishesInProcessing() {
        return dishesInProcessing;
    }

    public void setDishesInProcessing(List<Dish> dishesInProcessing) {
        this.dishesInProcessing = dishesInProcessing;
    }

    @ManyToMany
    @JoinTable(name = "DISH_APPROVED_WITH_BASKET",
            joinColumns = @JoinColumn(name = "BASKET_ID"),
            inverseJoinColumns = @JoinColumn(name = "DISH_ID"))
    public List<Dish> getDishesApproved() {
        return dishesApproved;
    }

    public void setDishesApproved(List<Dish> dishesApproved) {
        this.dishesApproved = dishesApproved;
    }

    public Cart(Long id, List<Dish> dishes, List<Dish> dishesInProcessing, List<Dish> dishesApproved, User user) {
        this.id = id;
        this.dishes = dishes;
        this.dishesInProcessing = dishesInProcessing;
        this.dishesApproved = dishesApproved;
        this.user = user;
    }

    public Cart(User user) {
        setUser(user);
    }

    public Cart() {
    }

    public Cart(Long id, List<Dish> dishes, List<Dish> dishesInProcessing, List<Dish> dishesApproved) {
        this.id = id;
        this.dishes = dishes;
        this.dishesInProcessing = dishesInProcessing;
        this.dishesApproved = dishesApproved;
    }
}
