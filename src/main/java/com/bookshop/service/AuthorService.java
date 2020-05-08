package com.bookshop.service;

import com.bookshop.model.dataService.AuthorDataService;
import com.bookshop.model.entity.Cafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorDataService authorDataService;

    @Autowired
    private AuthorImageService authorImageService;

    public void save(Cafe cafe) {
        authorDataService.save(cafe);
    }

    public Optional<Cafe> findBySurnameAndName(String surname, String name) {
        if (!StringUtils.isEmpty(surname) && !StringUtils.isEmpty(name)) {
            return authorDataService.findBySurnameAndName(surname, name);
        } else {
            return Optional.empty();
        }
    }

    public List<Cafe> findBySurnameOrName(String surname, String name) {
        return authorDataService.findBySurnameOrName(surname, name);
    }

    public void deleteById(long id) {
        authorDataService.deleteById(id);
    }

    public Cafe findById(long id) {
        return authorDataService.findById(id);
    }

    public List<Cafe> findAll() {
        return authorDataService.findAll();
    }

    public void update(String surname, String name, Cafe cafe, MultipartFile image) throws IOException {
        cafe.setSurname(surname);
        cafe.setName(name);
        Long authorImageToDelete = null;
        if (image != null && image.getOriginalFilename() != null && !image.getOriginalFilename().isEmpty()) {
            if (cafe.getImage() != null) {
                authorImageToDelete = cafe.getImage().getId();
            }
            authorImageService.add(image, cafe);
            if (authorImageToDelete != null)
                authorImageService.deleteById(authorImageToDelete);
        }
        save(cafe);
    }

    public void create(String surname, String name) {
        Cafe cafe = new Cafe(surname, name);
        save(cafe);
    }
}
