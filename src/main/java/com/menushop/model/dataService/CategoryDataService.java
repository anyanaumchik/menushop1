package com.menushop.model.dataService;

import com.menushop.model.dao.CategoryDAO;
import com.menushop.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDataService {

    @Autowired
    private CategoryDAO categoryDAO;

    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    public Category findById(long id) {
        return categoryDAO.findById(id);
    }

    public Category findFirstByTitleEnOrTitleRu(String titleEn, String titleRu) {
        return categoryDAO.findFirstByTitleEnOrTitleRu(titleEn, titleRu);
    }

    public void save(Category category) {
        categoryDAO.save(category);
    }
}
