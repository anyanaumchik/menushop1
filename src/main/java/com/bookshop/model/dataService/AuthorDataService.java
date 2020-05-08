package com.bookshop.model.dataService;

import com.bookshop.model.dao.AuthorDAO;
import com.bookshop.model.entity.Cafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AuthorDataService {

    @Autowired
    private AuthorDAO authorDAO;

    public List<Cafe> findAll() {
        return authorDAO.findAll();
    }

    public Cafe findById(long id) {
        return authorDAO.findById(id);
    }

    public void deleteById(long id) {
        authorDAO.deleteById(id);
    }

    public Optional<Cafe> findBySurnameAndName(String surname, String name) {
        return authorDAO.findBySurnameAndName(surname, name);
    }

    public List<Cafe> findBySurnameOrName(String surname, String name) {
        return authorDAO.findBySurnameOrName(surname, name);
    }

    public void save(Cafe cafe) {
        authorDAO.save(cafe);
    }
}
