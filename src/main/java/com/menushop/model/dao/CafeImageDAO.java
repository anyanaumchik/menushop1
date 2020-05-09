package com.menushop.model.dao;

import com.menushop.model.entity.CafeImage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CafeImageDAO extends CrudRepository<CafeImage, Long> {

    List<CafeImage> findAll();

    CafeImage findById(long id);

    void deleteById(long id);
}
