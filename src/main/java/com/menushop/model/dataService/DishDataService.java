package com.menushop.model.dataService;

import com.menushop.model.dao.DishDAO;
import com.menushop.model.entity.Cafe;
import com.menushop.model.entity.Dish;
import com.menushop.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("bookDAO")
public class DishDataService {

    @Autowired
    private DishDAO dishDAO;

    public List<Dish> getLastDishes() {
        return dishDAO.findByOrderByIdDesc();
    }

    public void save(Dish dish) {
        dishDAO.save(dish);
    }

    public Dish findById(long id) {
        return dishDAO.findById(id);
    }

    public List<Dish> findAll() {
        return dishDAO.findAll();
    }

    public Page<Dish> findAllByCafe(Cafe cafe, Pageable pageable) {
        return dishDAO.findAllByCafe(cafe, pageable);
    }

    public Page<Dish> findAllByCategories(Category category, Pageable pageable) {
        return dishDAO.findAllByCategories(category, pageable);
    }

    public Page<Dish> findAllPage(Pageable pageable) {
        return dishDAO.findByOrderByTitleEnAsc(pageable);
    }

    public List<Dish> findByTitleEnOrTitleRu(String titleEn, String titleRu) {
        return dishDAO.findByTitleEnOrTitleRu(titleEn, titleRu);
    }

    public void deleteById(long id) {
        dishDAO.deleteById(id);
    }

}
