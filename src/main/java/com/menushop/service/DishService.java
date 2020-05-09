package com.menushop.service;

import com.menushop.model.dataService.DishDataService;
import com.menushop.model.entity.Cafe;
import com.menushop.model.entity.Dish;
import com.menushop.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Qualifier("dishService")
public class DishService {

    @Autowired
    private DishDataService dishDataService;

    @Autowired
    private DishImageService dishImageService;

    @Autowired
    private CafeService cafeService;

    @Autowired
    private CategoryService categoryService;



    public List<Dish> getLastDishes() {
        return dishDataService.getLastDishes();
    }

    public void create(double price, String titleRu, String titleEn, String description, Cafe cafe, Map<String, String> form, MultipartFile image) throws IOException {
        Dish dish = new Dish(price, titleRu, titleEn, description);
        dish.setCafe(cafe);
        List<Category> category = new ArrayList<>();
        for (String s : form.keySet()) {
            if (form.get(s).equals("on")) {
                category.add(categoryService.findById(Long.parseLong(s)));
            }
        }
        dish.setCategories(category);
        save(dish);
        dishImageService.add(image, dish);
    }

    public void save(Dish dish) {
        dishDataService.save(dish);
    }

    public Dish findById(long id) {
        return dishDataService.findById(id);
    }

    public List<Dish> findAll() {
        return dishDataService.findAll();
    }

    public List<Dish> findByTitleEnOrTitleRu(String titleEn, String titleRu) {
        return dishDataService.findByTitleEnOrTitleRu(titleEn, titleRu);
    }

    public Page<Dish> findAllPage(Pageable pageable) {
        return dishDataService.findAllPage(pageable);
    }

    public void deleteById(long id) {
        dishDataService.deleteById(id);
    }

    public Page<Dish> findAllByCafe(Cafe cafe, Pageable pageable) {
        return dishDataService.findAllByCafe(cafe, pageable);
    }

    public Page<Dish> findAllByCategories(Category category, Pageable pageable) {
        return dishDataService.findAllByCategories(category, pageable);
    }

    public void update(Dish dish, double price, String titleEn, String titleRu, String authorName, String description, Map<String, String> form, MultipartFile image) throws IOException {
        dish.setPrice(price);
        dish.setTitleRu(titleRu);
        dish.setTitleEn(titleEn);
        if (cafeService.findByName(authorName).isPresent())
            dish.setCafe(cafeService.findByName(authorName).get());
        dish.setDescription(description);
        dish.getCategories().clear();
        for (String s : form.keySet()) {
            if (form.get(s).equals("on")) {
                dish.getCategories().add(categoryService.findById(Long.parseLong(s)));
            }
        }
        Long imageIdToDelete = null;
        if (image != null && image.getOriginalFilename() != null && !image.getOriginalFilename().isEmpty()) {
            if (dish.getImage() != null) {
                imageIdToDelete = dish.getImage().getId();
            }
            dishImageService.add(image, dish);
            if (imageIdToDelete != null)
                dishImageService.deleteById(imageIdToDelete);
        }
        save(dish);
    }
}
