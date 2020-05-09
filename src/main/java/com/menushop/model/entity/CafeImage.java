package com.menushop.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CAFE_IMAGE")
public class CafeImage {

    private Long id;
    private String cafeImage;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "IMAGE")
    public String getCafeImage() {
        return cafeImage;
    }

    public void setCafeImage(String cafeImage) {
        this.cafeImage = cafeImage;
    }



    public CafeImage(Long id, String cafeImage) {
        this.id = id;
        this.cafeImage = cafeImage;

    }

    public CafeImage(String cafeImage) {
        this.cafeImage = cafeImage;

    }

    public CafeImage() {
    }

}
