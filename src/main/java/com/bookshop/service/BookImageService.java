package com.bookshop.service;

import com.bookshop.model.dataService.BookImageDataService;
import com.bookshop.model.entity.Book;
import com.bookshop.model.entity.BookImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class BookImageService {

    @Autowired
    private BookImageDataService bookImageDTO;

    @Autowired
    private BookService bookService;

    @Value("${upload.path.book}")
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

    public void add(MultipartFile image, Book book) throws IOException {
        if (image != null && !image.getOriginalFilename().isEmpty() && image.getOriginalFilename() != null) {
            BookImage book_image = new BookImage();
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String fileName = image.getOriginalFilename();
            book_image.setBookImage(fileName);
            save(book_image);
            book.setImage(book_image);
            bookService.save(book);
        }
    }
}
