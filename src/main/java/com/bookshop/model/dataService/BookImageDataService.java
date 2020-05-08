package com.bookshop.model.dataService;

import com.bookshop.model.dao.BookImageDAO;
import com.bookshop.model.entity.BookImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookImageDataService {

    @Autowired
    private BookImageDAO bookImageDAO;

    public void save(BookImage bookImage) {
        bookImageDAO.save(bookImage);
    }

    public List<BookImage> findAll() {
        return bookImageDAO.findAll();
    }

    public BookImage findById(long id) {
        return bookImageDAO.findById(id);
    }

    public void deleteById(long id) {
        bookImageDAO.deleteById(id);
    }

}
