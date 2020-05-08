package com.bookshop.model.dataService;

import com.bookshop.model.dao.BookDAO;
import com.bookshop.model.entity.Author;
import com.bookshop.model.entity.Book;
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

    public List<Book> getLastBooks() {
        return bookDAO.findByOrderByIdDesc();
    }

    public void save(Book book) {
        bookDAO.save(book);
    }

    public Book findById(long id) {
        return bookDAO.findById(id);
    }

    public List<Book> findAll() {
        return bookDAO.findAll();
    }

    public Page<Book> findAllByAuthor(Author author, Pageable pageable) {
        return bookDAO.findAllByAuthor(author, pageable);
    }

    public Page<Book> findAllByCategories(Category category, Pageable pageable) {
        return bookDAO.findAllByCategories(category, pageable);
    }

    public Page<Book> findAllPage(Pageable pageable) {
        return bookDAO.findByOrderByTitleEnAsc(pageable);
    }

    public List<Book> findByTitleEnOrTitleRu(String titleEn, String titleRu) {
        return bookDAO.findByTitleEnOrTitleRu(titleEn, titleRu);
    }

    public void deleteById(long id) {
        bookDAO.deleteById(id);
    }

}
