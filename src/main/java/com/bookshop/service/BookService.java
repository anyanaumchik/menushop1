package com.bookshop.service;

import com.bookshop.model.dataService.BookDataService;
import com.bookshop.model.entity.Cafe;
import com.bookshop.model.entity.Dish;
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
    private CafeService cafeService;

    @Autowired
    private CategoryService categoryService;

//    public void create(Book book, Author author) {
//        book.setAuthor(author);
//        save(book);
//    }

    public List<Dish> getLastBooks() {
        return bookDataService.getLastBooks();
    }

    public void create(double price, String titleRu, String titleEn, String description, Cafe cafe, Map<String, String> form, MultipartFile image) throws IOException {
//        Book book = new Book(price, titleRu, titleEn, description);
        Dish dish = new Dish(price, titleRu, titleEn, description);
        dish.setCafe(cafe);
        List<Category> category = new ArrayList<>();
        for (String s : form.keySet()) {
            if (form.get(s).equals("on")) {
                category.add(categoryService.findById(Long.parseLong(s)));
//                book.getCategories().add(categoryService.findById(Long.valueOf(s)));
            }
        }
        dish.setCategories(category);
        save(dish);
        bookImageService.add(image, dish);
    }

    public void save(Dish dish) {
        bookDataService.save(dish);
    }

    public Dish findById(long id) {
        return bookDataService.findById(id);
    }

    public List<Dish> findAll() {
        return bookDataService.findAll();
    }

    public List<Dish> findByTitleEnOrTitleRu(String titleEn, String titleRu) {
        return bookDataService.findByTitleEnOrTitleRu(titleEn, titleRu);
    }

    public Page<Dish> findAllPage(Pageable pageable) {
        return bookDataService.findAllPage(pageable);
    }

    public void deleteById(long id) {
        bookDataService.deleteById(id);
    }

    public Page<Dish> findAllByAuthor(Cafe cafe, Pageable pageable) {
        return bookDataService.findAllByAuthor(cafe, pageable);
    }

    public Page<Dish> findAllByCategories(Category category, Pageable pageable) {
        return bookDataService.findAllByCategories(category, pageable);
    }

    public void update(Dish dish, double price, String titleEn, String titleRu, String authorName, String description, Map<String, String> form, MultipartFile image) throws IOException {
        dish.setPrice(price);
        dish.setTitleRu(titleRu);
        dish.setTitleEn(titleEn);
        if (cafeService.findByName(authorName).isPresent())
            dish.setCafe(cafeService.findByName(authorName).get());
        dish.setDescription(description);
        dish.getCategories().clear();
        for (String s : form.keySet()) {
            if (form.get(s).equals("on")) {
                dish.getCategories().add(categoryService.findById(Long.parseLong(s)));
            }
        }
        Long imageIdToDelete = null;
        if (image != null && image.getOriginalFilename() != null && !image.getOriginalFilename().isEmpty()) {
            if (dish.getImage() != null) {
                imageIdToDelete = dish.getImage().getId();
            }
            bookImageService.add(image, dish);
            if (imageIdToDelete != null)
                bookImageService.deleteById(imageIdToDelete);
        }
        save(dish);
    }
}
