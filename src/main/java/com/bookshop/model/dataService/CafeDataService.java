package com.bookshop.model.dataService;

import com.bookshop.model.dao.CafeDAO;
import com.bookshop.model.entity.Cafe;
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

    public Optional<Cafe> findBySurnameAndName(String surname, String name) {
        return cafeDAO.findBySurnameAndName(surname, name);
    }

    public List<Cafe> findBySurnameOrName(String surname, String name) {
        return cafeDAO.findBySurnameOrName(surname, name);
    }

    public void save(Cafe cafe) {
        cafeDAO.save(cafe);
    }
}
