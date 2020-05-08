package com.bookshop.model.dao;


import com.bookshop.model.entity.Author;
import com.bookshop.model.entity.Book;
import com.bookshop.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BookDAO extends CrudRepository<Book, Long> {

    List<Book> findAll();

    List<Book> findByOrderByIdDesc();

    Page<Book> findByOrderByTitleEnAsc(Pageable pageable);

    Page<Book> findAllByAuthor(Author author, Pageable pageable);

    Page<Book> findAllByCategories(Category category, Pageable pageable);

    Book findById(long id);

    void deleteById(long id);

    List<Book> findAllByPrice(double price);

    List<Book> findByTitleEnOrTitleRu(String titleEn, String titleRu);
}
