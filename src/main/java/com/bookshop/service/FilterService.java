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
    private CafeService cafeService;

    public List<Cafe> authorsFilter(@RequestParam(defaultValue = "") String filter) {
        List<String> items = Arrays.asList(filter.split("\\s* \\s*"));
        List<Cafe> cafes = new ArrayList<>();
        if (items.size() > 1) {
            boolean present = cafeService.findBySurnameAndName(items.get(0), items.get(1)).isPresent();
            boolean present1 = cafeService.findBySurnameAndName(items.get(1), items.get(0)).isPresent();
            if (present) {
                cafes.add(cafeService.findBySurnameAndName(items.get(0), items.get(1)).get());
            }
            if (present1) {
                cafes.add(cafeService.findBySurnameAndName(items.get(1), items.get(0)).get());
            }
            if (!present && !present1) {
                for (String item : items) {
                    if (!cafes.containsAll(cafeService.findBySurnameOrName(item, item)))
                        cafes.addAll(cafeService.findBySurnameOrName(item, item));
                }
            }
        } else {
            cafes.addAll(cafeService.findBySurnameOrName(filter, filter));
        }
        return cafes;
    }
}
