package com.example.servicestoreproject.mapper;
import com.example.servicestoreproject.dto.StoreDto;
import com.example.servicestoreproject.entity.store;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface StoreMapper {

    StoreDto toStoreDto(store thestore);

    store toStore(StoreDto storeDto);
}
