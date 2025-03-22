package com.example.servicestoreproject.mapper;
import com.example.servicestoreproject.dto.StockHistoryDto;
import com.example.servicestoreproject.dto.StoreDto;
import com.example.servicestoreproject.entity.StockHistory;
import com.example.servicestoreproject.entity.store;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")

public interface StockHistoryMapper {
    StockHistoryDto toStockHistoryDto(StockHistory stockHistory);

    StockHistory toStockHistory(StockHistoryDto StockHistoryDto);
}
