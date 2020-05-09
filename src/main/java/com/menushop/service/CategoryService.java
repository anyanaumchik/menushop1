package com.menushop.service;

import com.menushop.model.dataService.CategoryDataService;
import com.menushop.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryDataService categoryDataService;

    public List<Category> findAll() {
        return categoryDataService.findAll();
    }

    public Category findById(long id) {
        return categoryDataService.findById(id);
    }

    public Category findFirstByTitleEnOrTitleRu(String titleEn, String titleRu) {
        return categoryDataService.findFirstByTitleEnOrTitleRu(titleEn, titleRu);
    }

    public void create(String titleEn, String titleRu) {
        save(new Category(titleEn, titleRu));
    }

    public void save(Category category) {
        categoryDataService.save(category);
    }
}
