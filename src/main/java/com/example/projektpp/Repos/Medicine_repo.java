package com.example.projektpp.Repos;

import com.example.projektpp.models.Category;
import com.example.projektpp.models.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Medicine_repo extends JpaRepository<Medicine, Long> {
    @Query("select m from Medicine m, Category c where m.category.id=:id")
    public List<Medicine> getMedicineByCategory(Integer id);
}
