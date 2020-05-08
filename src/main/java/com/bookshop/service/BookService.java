package com.bookshop.service;

import com.bookshop.model.dataService.BookDataService;
import com.bookshop.model.entity.Author;
import com.bookshop.model.entity.Book;
import com.bookshop.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Qualifier("bookService")
public class BookService {

    @Autowired
    private BookDataService bookDataService;

    @Autowired
    private BookImageService bookImageService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;

//    public void create(Book book, Author author) {
//        book.setAuthor(author);
//        save(book);
//    }

    public List<Book> getLastBooks() {
        return bookDataService.getLastBooks();
    }

    public void create(double price, String titleRu, String titleEn, String description, Author author, Map<String, String> form, MultipartFile image) throws IOException {
//        Book book = new Book(price, titleRu, titleEn, description);
        Book book = new Book(price, titleRu, titleEn, description);
        book.setAuthor(author);
        List<Category> category = new ArrayList<>();
        for (String s : form.keySet()) {
            if (form.get(s).equals("on")) {
                category.add(categoryService.findById(Long.parseLong(s)));
//                book.getCategories().add(categoryService.findById(Long.valueOf(s)));
            }
        }
        book.setCategories(category);
        save(book);
        bookImageService.add(image, book);
    }

    public void save(Book book) {
        bookDataService.save(book);
    }

    public Book findById(long id) {
        return bookDataService.findById(id);
    }

    public List<Book> findAll() {
        return bookDataService.findAll();
    }

    public List<Book> findByTitleEnOrTitleRu(String titleEn, String titleRu) {
        return bookDataService.findByTitleEnOrTitleRu(titleEn, titleRu);
    }

    public Page<Book> findAllPage(Pageable pageable) {
        return bookDataService.findAllPage(pageable);
    }

    public void deleteById(long id) {
        bookDataService.deleteById(id);
    }

    public Page<Book> findAllByAuthor(Author author, Pageable pageable) {
        return bookDataService.findAllByAuthor(author, pageable);
    }

    public Page<Book> findAllByCategories(Category category, Pageable pageable) {
        return bookDataService.findAllByCategories(category, pageable);
    }

    public void update(Book book, double price, String titleEn, String titleRu, String authorSurname, String authorName, String description, Map<String, String> form, MultipartFile image) throws IOException {
        book.setPrice(price);
        book.setTitleRu(titleRu);
        book.setTitleEn(titleEn);
        if (authorService.findBySurnameAndName(authorSurname, authorName).isPresent())
            book.setAuthor(authorService.findBySurnameAndName(authorSurname, authorName).get());
        book.setDescription(description);
        book.getCategories().clear();
        for (String s : form.keySet()) {
            if (form.get(s).equals("on")) {
                book.getCategories().add(categoryService.findById(Long.parseLong(s)));
            }
        }
        Long imageIdToDelete = null;
        if (image != null && image.getOriginalFilename() != null && !image.getOriginalFilename().isEmpty()) {
            if (book.getImage() != null) {
                imageIdToDelete = book.getImage().getId();
            }
            bookImageService.add(image, book);
            if (imageIdToDelete != null)
                bookImageService.deleteById(imageIdToDelete);
        }
        save(book);
    }
}
