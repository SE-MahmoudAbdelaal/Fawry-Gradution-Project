package com.example.servicestoreproject.service.store;

import com.example.servicestoreproject.dto.StoreDto;
import com.example.servicestoreproject.entity.store;

import java.util.List;

public interface StoreService {
    public void addStore(StoreDto storeDto);

    public StoreDto getStore(long id);

    public List<StoreDto> getAllStores();

    public void deleteStore(long id);

    public void updateStore(long id,StoreDto storeDto);

}
