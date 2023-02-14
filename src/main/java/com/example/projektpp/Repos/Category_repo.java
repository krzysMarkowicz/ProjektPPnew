package com.example.projektpp.Repos;

import com.example.projektpp.models.Category;
import com.example.projektpp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Category_repo extends JpaRepository<Category, Long> {
    @Query("select c from Category c")
    public List<Category> getCategory();
}
