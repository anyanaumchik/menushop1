package com.bookshop.service;

import com.bookshop.model.dataService.AuthorImageDataService;
import com.bookshop.model.entity.Author;
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
public class AuthorImageService {

    @Autowired
    private AuthorImageDataService authorImageDataService;

    @Autowired
    private AuthorService authorService;

    @Value("${upload.path.author}")
    private String uploadPath;

    public void save(AuthorImage authorImage) {
        authorImageDataService.save(authorImage);
    }

    public List<AuthorImage> findAll() {
        return authorImageDataService.findAll();
    }

    public AuthorImage findById(long id) {
        return authorImageDataService.findById(id);
    }

    public void deleteById(long id) {
        authorImageDataService.deleteById(id);
    }

    public void add(MultipartFile image, Author author) throws IOException {
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
            author.setImage(authorImage);
            authorService.save(author);
        }
    }
}
