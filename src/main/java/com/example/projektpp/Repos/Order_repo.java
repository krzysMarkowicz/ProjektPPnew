package com.example.projektpp.Repos;

import com.example.projektpp.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Order_repo extends JpaRepository<Order, Long> {
}
