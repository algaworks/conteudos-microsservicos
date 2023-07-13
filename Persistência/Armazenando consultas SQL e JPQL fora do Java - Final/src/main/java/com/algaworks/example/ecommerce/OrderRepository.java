package com.algaworks.example.ecommerce;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(name = "Order.findByCustomerName", nativeQuery = false)
    List<Order> findByCustomer(@Param("customerName") String customerName);

    @Query(name = "Order.countPaidOrders")
    Integer countPaidOrders();

}
