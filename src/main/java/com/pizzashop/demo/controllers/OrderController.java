package com.pizzashop.demo.controllers;

import com.pizzashop.demo.domain.DiscountCalculator;
import com.pizzashop.demo.domain.TotalCostCalculator;
import com.pizzashop.demo.dtos.OrderDto;
import com.pizzashop.demo.entities.Order;
import com.pizzashop.demo.services.IOrderDetailService;
import com.pizzashop.demo.services.IOrderService;
import com.pizzashop.demo.services.IProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class OrderController {

    private final IOrderService orderService;
    private final IOrderDetailService orderDetailService;
    private final IProductService productService;

    public OrderController(IOrderService orderService, IOrderDetailService orderDetailService, IProductService productService) {
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
        this.productService = productService;
    }

    @PostMapping("/orders")
    public Order save(@Valid @RequestBody OrderDto orderDto) {
        var order = orderService.save(orderDto);
        var detailsOrderList = orderDetailService.save(orderDto.details(), order);
        order.setDetails(detailsOrderList);
        return order;
    }

    @GetMapping("/orders")
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/orders/{date}")
    public List<Order> getByDate(@PathVariable Date date) {
        return orderService.getByDate(date);
    }
}

