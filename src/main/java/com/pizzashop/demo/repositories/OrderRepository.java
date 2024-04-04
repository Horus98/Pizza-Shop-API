package com.pizzashop.demo.repositories;

import com.pizzashop.demo.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findAll();
    public List<Order> findByDate(Date date);
}
