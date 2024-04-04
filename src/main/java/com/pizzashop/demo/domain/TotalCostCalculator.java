package com.pizzashop.demo.domain;

import com.pizzashop.demo.dtos.DetailsDto;
import com.pizzashop.demo.entities.Order;
import com.pizzashop.demo.entities.OrderDetail;
import com.pizzashop.demo.services.IProductService;
import com.pizzashop.demo.services.ProductService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TotalCostCalculator {

    private final IProductService productService;
    private float DISCOUNT = 0.7f;

    public TotalCostCalculator(IProductService productService) {
        this.productService = productService;
    }

    public float calculate(List<DetailsDto> detailsDtoList, boolean hasDiscount) {
        var cost = 0f;
        for (DetailsDto detailsDto: detailsDtoList) {
            cost += detailsDto.amount() * productService.get(detailsDto.product_id()).getUnitPrice();
        }
        return hasDiscount ? cost * DISCOUNT : cost;
    }
}
