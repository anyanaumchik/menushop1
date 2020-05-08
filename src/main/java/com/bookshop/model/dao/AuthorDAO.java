package com.bookshop.model.dao;


import com.bookshop.model.entity.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorDAO extends CrudRepository<Author, Long> {

    List<Author> findAll();

    Author findById(long id);

    void deleteById(long id);

    Optional<Author> findBySurnameAndName(String surname, String name);

    List<Author> findBySurnameOrName(String surname, String name);
}
