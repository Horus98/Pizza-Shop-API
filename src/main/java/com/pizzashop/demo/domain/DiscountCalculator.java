package com.pizzashop.demo.domain;

import com.pizzashop.demo.dtos.DetailsDto;

import java.util.List;

public class DiscountCalculator {
    private final List<DetailsDto> detailsDtoList;

    public DiscountCalculator(List<DetailsDto> detailsDtoList) {
        this.detailsDtoList = detailsDtoList;
    }

    public boolean isDiscountApplicable() {
        return detailsDtoList.stream().mapToInt(DetailsDto::amount).sum() >= 3;
    }

}
