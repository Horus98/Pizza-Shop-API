package com.pizzashop.demo.services;

import com.pizzashop.demo.dtos.DetailsDto;
import com.pizzashop.demo.entities.Order;
import com.pizzashop.demo.entities.OrderDetail;

import java.util.List;
import java.util.Set;

public interface IOrderDetailService {
    public Set<OrderDetail> save(List<DetailsDto> detailsDto, Order order);
}
