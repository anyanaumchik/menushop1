package com.bookshop.model.dataService;

import com.bookshop.model.dao.BookDAO;
import com.bookshop.model.entity.Cafe;
import com.bookshop.model.entity.Dish;
import com.bookshop.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("bookDAO")
public class BookDataService {

    @Autowired
    private BookDAO bookDAO;

    public List<Dish> getLastBooks() {
        return bookDAO.findByOrderByIdDesc();
    }

    public void save(Dish dish) {
        bookDAO.save(dish);
    }

    public Dish findById(long id) {
        return bookDAO.findById(id);
    }

    public List<Dish> findAll() {
        return bookDAO.findAll();
    }

    public Page<Dish> findAllByAuthor(Cafe cafe, Pageable pageable) {
        return bookDAO.findAllByCafe(cafe, pageable);
    }

    public Page<Dish> findAllByCategories(Category category, Pageable pageable) {
        return bookDAO.findAllByCategories(category, pageable);
    }

    public Page<Dish> findAllPage(Pageable pageable) {
        return bookDAO.findByOrderByTitleEnAsc(pageable);
    }

    public List<Dish> findByTitleEnOrTitleRu(String titleEn, String titleRu) {
        return bookDAO.findByTitleEnOrTitleRu(titleEn, titleRu);
    }

    public void deleteById(long id) {
        bookDAO.deleteById(id);
    }

}
