package com.example.projektpp.Serwisy;


import com.example.projektpp.Repos.Role_repo;
import com.example.projektpp.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    Role_repo rolerepo;

    public List<Role> findall(){
        return rolerepo.findAll();
    }

    public Optional<Role> findById(long id){
        return rolerepo.findById(id);
    }

    public void delete(long id){
        rolerepo.deleteById(id);
    }

    public void save(Role role){
        rolerepo.save(role);
    }
}
