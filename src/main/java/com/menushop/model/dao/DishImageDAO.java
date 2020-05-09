package com.menushop.model.dao;

import com.menushop.model.entity.BookImage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DishImageDAO extends CrudRepository<BookImage, Long> {

    List<BookImage> findAll();

    BookImage findById(long id);

    void deleteById(long id);

}
