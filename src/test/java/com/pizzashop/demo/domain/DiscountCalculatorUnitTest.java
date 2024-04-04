package com.pizzashop.demo.domain;

import com.pizzashop.demo.dtos.DetailsDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountCalculatorUnitTest {

    @Test
    public void givenDetailsDtoListWithDiscountWhenCheckingIfDiscountApplicableThenDiscountShouldBeApplicable() {
        var discountCalculator = new DiscountCalculator(getDetailsDtoList());

        var hasDiscount = discountCalculator.isDiscountApplicable();

        assertEquals(true, hasDiscount);
    }

    @Test
    public void givenDetailsDtoListWithNonDiscountWhenCheckingIfDiscountApplicableThenDiscountShouldNotBeApplicable() {
        var discountCalculator = new DiscountCalculator(getDetailsDtoListWithoutDiscount());

        var hasDiscount = discountCalculator.isDiscountApplicable();

        assertEquals(false, hasDiscount);
    }

    private List<DetailsDto> getDetailsDtoList() {
        var dto = new DetailsDto(1,1l);
        var dto2 = new DetailsDto(3,1l);
        var dto3 = new DetailsDto(4,1l);
        var dto4 = new DetailsDto(4,1l);
        return Arrays.asList(dto, dto2, dto3, dto4);
    }

    private List<DetailsDto> getDetailsDtoListWithoutDiscount() {
        var dto = new DetailsDto(1,1l);
        var dto2 = new DetailsDto(1,1l);
        return Arrays.asList(dto, dto2);
    }
}
