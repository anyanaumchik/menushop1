package com.menushop.model.dao;

import com.menushop.model.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleDAO extends CrudRepository<Role, Long> {

    List<Role> findAll();

    Role findById(long id);

    Role findByTitle(String title);
}
