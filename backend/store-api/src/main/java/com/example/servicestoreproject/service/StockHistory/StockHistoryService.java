package com.example.servicestoreproject.service.StockHistory;

import com.example.servicestoreproject.entity.StockHistory;

import java.util.List;

public interface StockHistoryService {
    public void addStock(long productid, int amount);
    public void consumeStock(long productid, int amount);
    public List<StockHistory>getAllStockHistories();

    public List<StockHistory>getStockHistoryByid(long id);
    public boolean checkAvailability(long id);

}
