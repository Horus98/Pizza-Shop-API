package com.pizzashop.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzashop.demo.dataloaders.ProductDataLoader;
import com.pizzashop.demo.entities.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductControllerIntegrationTest {
    @Autowired
    private ProductDataLoader dataLoader;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        dataLoader.loadTestData();
    }

    @Test
    public void givenProductId_whenGetProductById_thenReturnsProduct() throws Exception {
        Product product = (new ObjectMapper()).readValue(
                mockMvc.perform(get("/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString(), Product.class
        );
        assertTrue(product.equals(ProductDataLoader.getProduct(1L)));
    }
}
