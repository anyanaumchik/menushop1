package com.bookshop.service;

import com.bookshop.model.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FilterService {

    @Autowired
    private AuthorService authorService;

    public List<Author> authorsFilter(@RequestParam(defaultValue = "") String filter) {
        List<String> items = Arrays.asList(filter.split("\\s* \\s*"));
        List<Author> authors = new ArrayList<>();
        if (items.size() > 1) {
            boolean present = authorService.findBySurnameAndName(items.get(0), items.get(1)).isPresent();
            boolean present1 = authorService.findBySurnameAndName(items.get(1), items.get(0)).isPresent();
            if (present) {
                authors.add(authorService.findBySurnameAndName(items.get(0), items.get(1)).get());
            }
            if (present1) {
                authors.add(authorService.findBySurnameAndName(items.get(1), items.get(0)).get());
            }
            if (!present && !present1) {
                for (String item : items) {
                    if (!authors.containsAll(authorService.findBySurnameOrName(item, item)))
                        authors.addAll(authorService.findBySurnameOrName(item, item));
                }
            }
        } else {
            authors.addAll(authorService.findBySurnameOrName(filter, filter));
        }
        return authors;
    }
}
