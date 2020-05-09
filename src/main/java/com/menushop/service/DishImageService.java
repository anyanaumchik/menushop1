package com.menushop.service;

import com.menushop.model.dataService.DishImageDataService;
import com.menushop.model.entity.Dish;
import com.menushop.model.entity.BookImage;
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
    private DishImageDataService bookImageDTO;

    @Autowired
    private DishService dishService;

    @Value("${upload.path.dish}")
    private String uploadPath;


    public void save(BookImage author_image) {
        bookImageDTO.save(author_image);
    }

    public List<BookImage> findAll() {
        return bookImageDTO.findAll();
    }

    public BookImage findById(long id) {
        return bookImageDTO.findById(id);
    }

    public void deleteById(long id) {
        bookImageDTO.deleteById(id);
    }

    public void add(MultipartFile image, Dish dish) throws IOException {
        if (image != null && !image.getOriginalFilename().isEmpty() && image.getOriginalFilename() != null) {
            BookImage book_image = new BookImage();
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String fileName = image.getOriginalFilename();
            book_image.setBookImage(fileName);
            save(book_image);
            dish.setImage(book_image);
            dishService.save(dish);
        }
    }
}
