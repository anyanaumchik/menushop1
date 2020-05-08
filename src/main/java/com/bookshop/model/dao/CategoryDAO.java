package com.bookshop.model.dao;

import com.bookshop.model.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryDAO extends CrudRepository<Category, Long> {

    List<Category> findAll();

    Category findById(long id);

    Category findFirstByTitleEnOrTitleRu(String titleEn, String titleRu);
}
