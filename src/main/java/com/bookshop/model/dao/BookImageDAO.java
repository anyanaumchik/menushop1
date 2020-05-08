package com.bookshop.model.dao;

import com.bookshop.model.entity.BookImage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookImageDAO extends CrudRepository<BookImage, Long> {

    List<BookImage> findAll();

    BookImage findById(long id);

    void deleteById(long id);

}
