package com.pizzashop.demo.services;

import com.pizzashop.demo.entities.Product;
import com.pizzashop.demo.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product get(Long id) {
        return productRepository.findById(id).orElseThrow(() -> getRuntimeException(id));
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product delete(Long id) {
        var product = productRepository.findById(id).orElseThrow(() -> getRuntimeException(id));
        productRepository.deleteById(id);
        return product;
    }

    public Product update(Long id, Product product) {
        var productToUpdate = productRepository.findById(id).orElseThrow(() -> getRuntimeException(id));
        productToUpdate.setName(product.getName());
        productToUpdate.setUnitPrice(product.getUnitPrice());
        productToUpdate.setShortDescription(product.getShortDescription());
        productToUpdate.setLargeDescription(product.getLargeDescription());
        productRepository.save(productToUpdate);
        return productToUpdate;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    private RuntimeException getRuntimeException(Long id) {
        return new RuntimeException("Product with id " + id + " not exists");
    }
}
