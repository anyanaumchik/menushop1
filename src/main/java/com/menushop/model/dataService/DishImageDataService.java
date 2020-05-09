package com.menushop.model.dataService;

import com.menushop.model.dao.DishImageDAO;
import com.menushop.model.entity.DishImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DishImageDataService {

    @Autowired
    private DishImageDAO dishImageDAO;

    public void save(DishImage dishImage) {
        dishImageDAO.save(dishImage);
    }

    public List<DishImage> findAll() {
        return dishImageDAO.findAll();
    }

    public DishImage findById(long id) {
        return dishImageDAO.findById(id);
    }

    public void deleteById(long id) {
        dishImageDAO.deleteById(id);
    }

}
