package com.pizzashop.demo.domain;

import com.pizzashop.demo.dtos.DetailsDto;
import com.pizzashop.demo.entities.Product;
import com.pizzashop.demo.services.IProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TotalCostCalculatorUnitTest {

    private TotalCostCalculator totalCostCalculator;
    private IProductService productService;

    @BeforeEach
    public void setup() {
        productService = mock(IProductService.class);
        totalCostCalculator = new TotalCostCalculator(productService);
    }

    @Test
    public void givenDetailsDtoListWithDiscountWhenCalculateTheTotalCostThenDiscountIsAppliedAndTheCostIsTheExpected() {
        when(productService.get(1L)).thenReturn(getDummyProduct(1l));
        when(productService.get(2L)).thenReturn(getDummyProduct(2l));

        var totalCost = totalCostCalculator.calculate(getDetailsDtoList(), true);

        assertEquals(280f,totalCost);
    }

    @Test
    public void givenDetailsDtoListWithoutDiscountWhenCalculateTheTotalCostThenDiscountIsNotAppliedAndTheCostIsTheExpected() {
        when(productService.get(1L)).thenReturn(getDummyProduct(1l));
        when(productService.get(2L)).thenReturn(getDummyProduct(2l));

        var totalCost = totalCostCalculator.calculate(getDetailsDtoListWithoutDiscount(), false);

        assertEquals(200f,totalCost);
    }

    private List<DetailsDto> getDetailsDtoList() {
        var dto = new DetailsDto(1,1l);
        var dto2 = new DetailsDto(3,2l);
        return Arrays.asList(dto, dto2);
    }

    private List<DetailsDto> getDetailsDtoListWithoutDiscount() {
        var dto = new DetailsDto(1,1l);
        var dto2 = new DetailsDto(1,2l);
        return Arrays.asList(dto, dto2);
    }

    private Product getDummyProduct(long id) {
        var product = new Product(id);
        product.setUnitPrice(100f);
        product.setName("Dummy name");
        product.setLargeDescription("Large description");
        product.setShortDescription("Short description");
        return product;
    }
}
