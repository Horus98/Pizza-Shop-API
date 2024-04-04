package com.pizzashop.demo.dataloaders;

import com.pizzashop.demo.entities.Product;
import com.pizzashop.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDataLoader {

    @Autowired
    private ProductRepository productRepository;

    public void loadTestData() {
        Product product1 = getProduct(1L);
        Product product2 = getProduct(2L);
        productRepository.save(product1);
        productRepository.save(product2);
    }

    public static Product getProduct(long id) {
        String random = "Soy una descripcion larga, espero que este producto sea de su utilidad";
        if (id == 1L) {
            return new Product(1L, "Product 1", "Short description 1", random, 10.0f);
        }
        return new Product(2L, "Product 2", "Short description 2", random, 10.0f);
    }

}
