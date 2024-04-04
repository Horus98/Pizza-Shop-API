package com.pizzashop.demo.services;

import com.pizzashop.demo.dtos.OrderDto;
import com.pizzashop.demo.entities.Order;

import java.sql.Date;
import java.util.List;

public interface IOrderService {

    public Order get(Long id);
    public List<Order> getAll();
    public List<Order> getByDate(Date date);
    public Order delete(Long id);
    public Order update(Long id, Order order);
    public Order save(OrderDto order);
}
