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
@EqualsAndHashCode
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    @Size(min = 4, max = 50)
    private String name;
    @NotNull
    @Size(min = 4, max = 40)
    private String shortDescription;
    @NotNull
    @Size(min = 40, max = 500)
    private String largeDescription;
    @Positive
    private float unitPrice;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<OrderDetail> orderDetails;

    public Product(Long id) {
        this.id = id;
    }

    public Product(Long id, String name, String shortDescription, String largeDescription, float unitPrice) {
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
        this.largeDescription = largeDescription;
        this.unitPrice = unitPrice;
    }
}
