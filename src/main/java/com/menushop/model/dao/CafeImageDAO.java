package com.menushop.model.dao;

import com.menushop.model.entity.AuthorImage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CafeImageDAO extends CrudRepository<AuthorImage, Long> {

    List<AuthorImage> findAll();

    AuthorImage findById(long id);

    void deleteById(long id);
}
