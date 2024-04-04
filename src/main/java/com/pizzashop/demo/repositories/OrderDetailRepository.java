package com.pizzashop.demo.repositories;

import com.pizzashop.demo.entities.OrderDetail;
import com.pizzashop.demo.entities.OrderDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailKey> {
}
