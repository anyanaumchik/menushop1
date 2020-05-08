package com.bookshop.service;

import com.bookshop.model.dataService.CafeImageDataService;
import com.bookshop.model.entity.Cafe;
import com.bookshop.model.entity.AuthorImage;
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

    public void save(AuthorImage authorImage) {
        cafeImageDataService.save(authorImage);
    }

    public List<AuthorImage> findAll() {
        return cafeImageDataService.findAll();
    }

    public AuthorImage findById(long id) {
        return cafeImageDataService.findById(id);
    }

    public void deleteById(long id) {
        cafeImageDataService.deleteById(id);
    }

    public void add(MultipartFile image, Cafe cafe) throws IOException {
        if (image != null && image.getOriginalFilename() != null && !image.getOriginalFilename().isEmpty()) {
            AuthorImage authorImage = new AuthorImage();
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String fileName = uuidFile + image.getOriginalFilename();
            image.transferTo(new File(uploadPath + "/" + fileName));
            authorImage.setAuthorImage(fileName);
//            authorImage.setAuthor(author);
            save(authorImage);
            cafe.setImage(authorImage);
            cafeService.save(cafe);
        }
    }
}