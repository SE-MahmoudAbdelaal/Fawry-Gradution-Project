package com.e_commerce.order_api.repository;

import com.e_commerce.order_api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
  Optional< List<Order>> findByCustomerId(Long customerId);
  Optional<List<Order>> findByCustomerIdAndCreatedAtBetween(Long customerId, LocalDate startDate, LocalDate endDate);
}
