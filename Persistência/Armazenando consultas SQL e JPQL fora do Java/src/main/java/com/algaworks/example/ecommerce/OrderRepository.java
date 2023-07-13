package com.algaworks.example.ecommerce;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select o from Order o " +
            "join fetch o.customer c " +
            "where c.name = :customerName"
    )
    List<Order> findByCustomer(@Param("customerName") String customerName);

    @Query(value = "select count(*) from \"order\" where status = 'PAID'", nativeQuery = true)
    Integer countPaidOrders();

}
