package com.menushop.service;

import com.menushop.model.dataService.DishImageDataService;
import com.menushop.model.entity.Dish;
import com.menushop.model.entity.DishImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class DishImageService {

    @Autowired
    private DishImageDataService dishImageDTO;

    @Autowired
    private DishService dishService;

    @Value("${upload.path.dish}")
    private String uploadPath;


    public void save(DishImage author_image) {
        dishImageDTO.save(author_image);
    }

    public List<DishImage> findAll() {
        return dishImageDTO.findAll();
    }

    public DishImage findById(long id) {
        return dishImageDTO.findById(id);
    }

    public void deleteById(long id) {
        dishImageDTO.deleteById(id);
    }

    public void add(MultipartFile image, Dish dish) throws IOException {
        if (image != null && !image.getOriginalFilename().isEmpty() && image.getOriginalFilename() != null) {
            DishImage dish_image = new DishImage();
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String fileName = image.getOriginalFilename();
            dish_image.setDishImage(fileName);
            save(dish_image);
            dish.setImage(dish_image);
            dishService.save(dish);
        }
    }
}
