package com.example.servicestoreproject.repositry;

import com.example.servicestoreproject.entity.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockHistoryRepo extends JpaRepository<StockHistory,Long> {

     List<StockHistory> findByProductId(long productId);
}
