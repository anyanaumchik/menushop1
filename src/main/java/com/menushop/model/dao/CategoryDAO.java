package com.menushop.model.dao;

import com.menushop.model.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryDAO extends CrudRepository<Category, Long> {

    List<Category> findAll();

    Category findById(long id);

    Category findFirstByTitleEnOrTitleRu(String titleEn, String titleRu);
}
