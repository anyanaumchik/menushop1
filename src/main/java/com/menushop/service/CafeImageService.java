package com.menushop.service;

import com.menushop.model.dataService.CafeImageDataService;
import com.menushop.model.entity.Cafe;
import com.menushop.model.entity.CafeImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class CafeImageService {

    @Autowired
    private CafeImageDataService cafeImageDataService;

    @Autowired
    private CafeService cafeService;

    @Value("${upload.path.cafe}")
    private String uploadPath;

    public void save(CafeImage cafeImage) {
        cafeImageDataService.save(cafeImage);
    }

    public List<CafeImage> findAll() {
        return cafeImageDataService.findAll();
    }

    public CafeImage findById(long id) {
        return cafeImageDataService.findById(id);
    }

    public void deleteById(long id) {
        cafeImageDataService.deleteById(id);
    }

    public void add(MultipartFile image, Cafe cafe) throws IOException {
        if (image != null && image.getOriginalFilename() != null && !image.getOriginalFilename().isEmpty()) {
            CafeImage cafeImage = new CafeImage();
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String fileName =  image.getOriginalFilename();
            cafeImage.setCafeImage(fileName);
            save(cafeImage);
            cafe.setImage(cafeImage);
            cafeService.save(cafe);
        }
    }
}
