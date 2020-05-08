package com.bookshop.model.dataService;

import com.bookshop.model.dao.CartDAO;
import com.bookshop.model.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartDataService {

    @Autowired
    private CartDAO cartDAO;

    public List<Cart> findAll() {
        return cartDAO.findAll();
    }

    public Cart findById(long id) {
        return cartDAO.findById(id);
    }

    public void save(Cart cart) {
        cartDAO.save(cart);
    }
}
