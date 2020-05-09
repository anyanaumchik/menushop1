package com.menushop.model.dataService;

import com.menushop.model.dao.RoleDAO;
import com.menushop.model.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleDataService {

    @Autowired
    private RoleDAO roleDAO;

    public List<Role> findAll() {
        return roleDAO.findAll();
    }

    public Role findById(long id) {
        return roleDAO.findById(id);

    }

    public Role findByTitle(String title) {
        return roleDAO.findByTitle(title);
    }
}
