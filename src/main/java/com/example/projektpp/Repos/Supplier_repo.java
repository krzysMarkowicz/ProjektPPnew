package com.example.projektpp.Repos;

import com.example.projektpp.models.Order;
import com.example.projektpp.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Supplier_repo extends JpaRepository<Supplier, Long> {
}
