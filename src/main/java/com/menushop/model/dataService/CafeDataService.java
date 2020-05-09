package com.menushop.model.dataService;

import com.menushop.model.dao.CafeDAO;
import com.menushop.model.entity.Cafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CafeDataService {

    @Autowired
    private CafeDAO cafeDAO;

    public List<Cafe> findAll() {
        return cafeDAO.findAll();
    }

    public Cafe findById(long id) {
        return cafeDAO.findById(id);
    }

    public void deleteById(long id) {
        cafeDAO.deleteById(id);
    }

    public Optional<Cafe> findByName(String name) {
        return cafeDAO.findByName( name);
    }

    public List<Cafe> findBySurnameOrName(String name1, String name) {
        return cafeDAO.findByNameOrName(name, name);
    }

    public void save(Cafe cafe) {
        cafeDAO.save(cafe);
    }
}
