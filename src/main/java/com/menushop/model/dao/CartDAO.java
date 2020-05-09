package com.menushop.model.dao;

import com.menushop.model.entity.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartDAO extends CrudRepository<Cart, Long> {

    List<Cart> findAll();

    Cart findById(long id);
}
