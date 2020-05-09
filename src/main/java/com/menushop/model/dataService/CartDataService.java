package com.menushop.model.dataService;

import com.menushop.model.dao.CartDAO;
import com.menushop.model.entity.Cart;
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
