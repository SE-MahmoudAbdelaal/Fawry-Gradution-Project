package com.example.servicestoreproject.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
public class StockHistoryDto {
    private long stockhistoryid;

    private String type;

    private int QuantityChange;

    private LocalDate TimeStamp;

    private Long productId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public long getStockhistoryid() {
        return stockhistoryid;
    }

    public void setStockhistoryid(long stockhistoryid) {
        this.stockhistoryid = stockhistoryid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantityChange() {
        return QuantityChange;
    }

    public void setQuantityChange(int quantityChange) {
        QuantityChange = quantityChange;
    }

    public LocalDate getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        TimeStamp = timeStamp;
    }
}
