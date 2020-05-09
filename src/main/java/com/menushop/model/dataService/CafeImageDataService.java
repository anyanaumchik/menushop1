package com.menushop.model.dataService;

import com.menushop.model.dao.CafeImageDAO;
import com.menushop.model.entity.CafeImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CafeImageDataService {

    @Autowired
    private CafeImageDAO cafeImageDAO;

    public void save(CafeImage author_image) {
        cafeImageDAO.save(author_image);
    }

    public List<CafeImage> findAll() {
        return cafeImageDAO.findAll();
    }

    public CafeImage findById(long id) {
        return cafeImageDAO.findById(id);
    }


    public void deleteById(long id) {
        cafeImageDAO.deleteById(id);
    }
}
