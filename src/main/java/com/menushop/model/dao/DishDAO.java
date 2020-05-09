package com.menushop.model.dao;


import com.menushop.model.entity.Cafe;
import com.menushop.model.entity.Dish;
import com.menushop.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface DishDAO extends CrudRepository<Dish, Long> {

    List<Dish> findAll();

    List<Dish> findByOrderByIdDesc();

    Page<Dish> findByOrderByTitleEnAsc(Pageable pageable);

    Page<Dish> findAllByCafe(Cafe cafe, Pageable pageable);

    Page<Dish> findAllByCategories(Category category, Pageable pageable);

    Dish findById(long id);

    void deleteById(long id);

    List<Dish> findAllByPrice(double price);

    List<Dish> findByTitleEnOrTitleRu(String titleEn, String titleRu);
}
