package com.pizzashop.demo.services;

import com.pizzashop.demo.dtos.DetailsDto;
import com.pizzashop.demo.entities.Order;
import com.pizzashop.demo.entities.OrderDetail;
import com.pizzashop.demo.entities.OrderDetailKey;
import com.pizzashop.demo.repositories.OrderDetailRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderDetailService implements IOrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final IProductService productService;


    public OrderDetailService(OrderDetailRepository orderDetailRepository, IProductService productService) {
        this.orderDetailRepository = orderDetailRepository;
        this.productService = productService;
    }

    public Set<OrderDetail> save(List<DetailsDto> detailsDtoList, Order order) {
        Set<OrderDetail> set = new HashSet<>();
        for (DetailsDto detailDto : detailsDtoList) {
            OrderDetail orderDetail = new OrderDetail();
            var product = productService.get(detailDto.product_id());
            orderDetail.setProduct(product);
            orderDetail.setAmount(detailDto.amount());
            orderDetail.setOrder(order);
            orderDetail.setName(product.getName());
            orderDetail.setCost(detailDto.amount() * product.getUnitPrice());
            var orderDetailKey = new OrderDetailKey(order.getId(), product.getId());
            orderDetail.setId(orderDetailKey);
            set.add(orderDetail);
            orderDetailRepository.save(orderDetail);
        }
        return set;
    }
}
