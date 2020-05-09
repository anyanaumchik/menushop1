package com.menushop.service;

import com.menushop.model.dataService.CafeDataService;
import com.menushop.model.entity.Cafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CafeService {

    @Autowired
    private CafeDataService cafeDataService;

    @Autowired
    private CafeImageService cafeImageService;

    public void save(Cafe cafe) {
        cafeDataService.save(cafe);
    }

    public Optional<Cafe> findByName(String name) {
        if (!StringUtils.isEmpty(name)) {
            return cafeDataService.findByName( name);
        } else {
            return Optional.empty();
        }
    }

    public List<Cafe> findBySurnameOrName(String surname, String name) {
        return cafeDataService.findBySurnameOrName(surname, name);
    }

    public void deleteById(long id) {
        cafeDataService.deleteById(id);
    }

    public Cafe findById(long id) {
        return cafeDataService.findById(id);
    }

    public List<Cafe> findAll() {
        return cafeDataService.findAll();
    }

    public void update(String name, Cafe cafe, MultipartFile image) throws IOException {
        cafe.setName(name);
        Long authorImageToDelete = null;
        if (image != null && image.getOriginalFilename() != null && !image.getOriginalFilename().isEmpty()) {
            if (cafe.getImage() != null) {
                authorImageToDelete = cafe.getImage().getId();
            }
            cafeImageService.add(image, cafe);
            if (authorImageToDelete != null)
                cafeImageService.deleteById(authorImageToDelete);
        }
        save(cafe);
    }

    public void create(String name) {
        Cafe cafe = new Cafe( name);
        save(cafe);
    }
}
