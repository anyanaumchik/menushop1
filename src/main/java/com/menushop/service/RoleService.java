package com.menushop.service;

import com.menushop.model.dataService.RoleDataService;
import com.menushop.model.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleDataService roleDataService;

    public List<Role> findAll() {
        return roleDataService.findAll();
    }

    public Role findById(long id) {
        return roleDataService.findById(id);
    }

    public Role findByTitle(String title) {
        return roleDataService.findByTitle(title);
    }
}
