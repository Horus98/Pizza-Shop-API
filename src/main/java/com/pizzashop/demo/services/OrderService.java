package com.pizzashop.demo.services;

import com.pizzashop.demo.domain.DiscountCalculator;
import com.pizzashop.demo.domain.TotalCostCalculator;
import com.pizzashop.demo.dtos.OrderDto;
import com.pizzashop.demo.entities.Order;
import com.pizzashop.demo.entities.State;
import com.pizzashop.demo.repositories.OrderRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@Primary
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final TotalCostCalculator totalCostCalculator;

    public OrderService (OrderRepository orderRepository, TotalCostCalculator totalCostCalculator) {
        this.orderRepository = orderRepository;
        this.totalCostCalculator = totalCostCalculator;
    }
    @Override
    public Order get(Long id) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getByDate(Date date) {
        return orderRepository.findByDate(date);
    }

    @Override
    public Order delete(Long id) {
        return null;
    }

    @Override
    public Order update(Long id, Order order) {
        return null;
    }

    @Override
    public Order save(OrderDto orderDto) {
        var hasDiscount = (new DiscountCalculator(orderDto.details())).isDiscountApplicable();
        var totalCost = totalCostCalculator.calculate(orderDto.details(), hasDiscount);
        Order order = new Order();
        order.setAddress(orderDto.address());
        order.setPhone(orderDto.phone());
        order.setEmail(orderDto.email());
        order.setTime(orderDto.time());
        order.setState(String.valueOf(State.OPEN));
        order.setDate(Date.valueOf(LocalDate.now()));
        order.setTotalCost(totalCost);
        order.setDiscountApplicable(hasDiscount);
        orderRepository.save(order);
        return order;
    }
}
