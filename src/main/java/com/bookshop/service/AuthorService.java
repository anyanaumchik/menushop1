package com.bookshop.service;

import com.bookshop.model.dataService.AuthorDataService;
import com.bookshop.model.entity.Author;
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

    public void save(Author author) {
        authorDataService.save(author);
    }

    public Optional<Author> findBySurnameAndName(String surname, String name) {
        if (!StringUtils.isEmpty(surname) && !StringUtils.isEmpty(name)) {
            return authorDataService.findBySurnameAndName(surname, name);
        } else {
            return Optional.empty();
        }
    }

    public List<Author> findBySurnameOrName(String surname, String name) {
        return authorDataService.findBySurnameOrName(surname, name);
    }

    public void deleteById(long id) {
        authorDataService.deleteById(id);
    }

    public Author findById(long id) {
        return authorDataService.findById(id);
    }

    public List<Author> findAll() {
        return authorDataService.findAll();
    }

    public void update(String surname, String name, Author author, MultipartFile image) throws IOException {
        author.setSurname(surname);
        author.setName(name);
        Long authorImageToDelete = null;
        if (image != null && image.getOriginalFilename() != null && !image.getOriginalFilename().isEmpty()) {
            if (author.getImage() != null) {
                authorImageToDelete = author.getImage().getId();
            }
            authorImageService.add(image, author);
            if (authorImageToDelete != null)
                authorImageService.deleteById(authorImageToDelete);
        }
        save(author);
    }

    public void create(String surname, String name) {
        Author author = new Author(surname, name);
        save(author);
    }
}
