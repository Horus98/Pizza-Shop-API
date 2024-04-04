package com.pizzashop.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    @Size(min = 4, max = 50)
    private String name;
    @NotNull
    @Size(min = 4, max = 100)
    private String shortDescription;
    @NotNull
    @Size(min = 100, max = 500)
    private String largeDescription;
    @Positive
    private float unitPrice;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<OrderDetail> orderDetails;

    public Product(Long id) {
        this.id = id;
    }
}
