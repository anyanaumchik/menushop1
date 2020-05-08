package com.bookshop.service;

import com.bookshop.model.entity.Cafe;
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

    public List<Cafe> authorsFilter(@RequestParam(defaultValue = "") String filter) {
        List<String> items = Arrays.asList(filter.split("\\s* \\s*"));
        List<Cafe> cafes = new ArrayList<>();
        if (items.size() > 1) {
            boolean present = authorService.findBySurnameAndName(items.get(0), items.get(1)).isPresent();
            boolean present1 = authorService.findBySurnameAndName(items.get(1), items.get(0)).isPresent();
            if (present) {
                cafes.add(authorService.findBySurnameAndName(items.get(0), items.get(1)).get());
            }
            if (present1) {
                cafes.add(authorService.findBySurnameAndName(items.get(1), items.get(0)).get());
            }
            if (!present && !present1) {
                for (String item : items) {
                    if (!cafes.containsAll(authorService.findBySurnameOrName(item, item)))
                        cafes.addAll(authorService.findBySurnameOrName(item, item));
                }
            }
        } else {
            cafes.addAll(authorService.findBySurnameOrName(filter, filter));
        }
        return cafes;
    }
}
