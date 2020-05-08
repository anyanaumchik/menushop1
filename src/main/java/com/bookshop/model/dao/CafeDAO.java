package com.bookshop.model.dao;


import com.bookshop.model.entity.Cafe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CafeDAO extends CrudRepository<Cafe, Long> {

    List<Cafe> findAll();

    Cafe findById(long id);

    void deleteById(long id);

    Optional<Cafe> findBySurnameAndName(String surname, String name);

    List<Cafe> findBySurnameOrName(String surname, String name);
}
