package com.pizzashop.demo.services;

import com.pizzashop.demo.entities.Product;

import java.util.List;

public interface IProductService {

    Product get(Long id);
    List<Product> getAll();
    Product delete(Long id);
    Product update(Long id, Product product);
    Product save(Product product);
}
