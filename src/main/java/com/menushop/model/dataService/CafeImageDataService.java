package com.menushop.model.dataService;

import com.menushop.model.dao.CafeImageDAO;
import com.menushop.model.entity.AuthorImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CafeImageDataService {

    @Autowired
    private CafeImageDAO cafeImageDAO;

    public void save(AuthorImage author_image) {
        cafeImageDAO.save(author_image);
    }

    public List<AuthorImage> findAll() {
        return cafeImageDAO.findAll();
    }

    public AuthorImage findById(long id) {
        return cafeImageDAO.findById(id);
    }


    public void deleteById(long id) {
        cafeImageDAO.deleteById(id);
    }
}
