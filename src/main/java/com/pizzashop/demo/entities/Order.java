package com.pizzashop.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull(message = "Debe ingresar una direccion")
    private String address;
    @NotNull
    private String phone;
    @Email
    private String email;
    @DateTimeFormat
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Float totalCost;
    private boolean isDiscountApplicable;
    private String state;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "HH:mm")
    @NotNull
    private LocalTime time;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> details;
    public Order(){}
}
