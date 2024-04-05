package com.pizzashop.demo.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzashop.demo.dataloaders.ProductDataLoader;
import com.pizzashop.demo.entities.Product;
import com.pizzashop.demo.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductControllerIntegrationTest {
    @Autowired
    private ProductDataLoader dataLoader;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public void setup() {
        dataLoader.loadTestData();
    }

    @Test
    @Transactional
    @Rollback
    public void givenProductId_whenGetProductById_thenReturnsProduct() throws Exception {
        Product product = (new ObjectMapper()).readValue(
                mockMvc.perform(get("/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString(), Product.class
        );
        assertEquals(product, ProductDataLoader.getProduct(1L));
    }

    @Test
    @Rollback
    @Transactional
    public void testGetAllProductsEndpoint() throws Exception {
        String responseBody = mockMvc.perform(get("/products"))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn()
                        .getResponse()
                .getContentAsString();
        List<Product> productList = (new ObjectMapper()).readValue(responseBody, new TypeReference<List<Product>>() {});
        List<Product> expectedList = List.of(ProductDataLoader.getProduct(1L), ProductDataLoader.getProduct(2L));
        assertEquals(expectedList, productList);
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteEndpoint() throws Exception {
        mockMvc.perform(delete("/products/{id}", 1L))
                .andExpect(status().isNoContent());

        var isProductPresentInDb = productRepository.findById(1L).isPresent();

        assertFalse(isProductPresentInDb);
    }

    @Test
    @Transactional
    @Rollback
    public void testCreateEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((new ObjectMapper()).writeValueAsString(getNewProduct())))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("New"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shortDescription").value("Short"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.largeDescription").value("Large Large Large Large Large Large Large Large Large"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.unitPrice").value(100f));
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateEndpoint() throws Exception {
        var product = getNewProduct();
        mockMvc.perform(MockMvcRequestBuilders.put("/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((new ObjectMapper()).writeValueAsString(getNewProduct())))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("New"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shortDescription").value("Short"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.largeDescription").value("Large Large Large Large Large Large Large Large Large"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.unitPrice").value(100f));
    }


        private Product getNewProduct() {
        var product = new Product();
        product.setName("New");
        product.setShortDescription("Short");
        product.setLargeDescription("Large Large Large Large Large Large Large Large Large");
        product.setUnitPrice(100f);
        return product;
    }
}
