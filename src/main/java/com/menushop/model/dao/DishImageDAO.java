package com.menushop.model.dao;

import com.menushop.model.entity.DishImage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DishImageDAO extends CrudRepository<DishImage, Long> {

    List<DishImage> findAll();

    DishImage findById(long id);

    void deleteById(long id);

}
