package com.example.servicestoreproject.mapper;

import com.example.servicestoreproject.dto.StoreDto;
import com.example.servicestoreproject.entity.store;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-11T21:15:58+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class StoreMapperImpl implements StoreMapper {

    @Override
    public StoreDto toStoreDto(store thestore) {
        if ( thestore == null ) {
            return null;
        }

        StoreDto storeDto = new StoreDto();

        if ( thestore.getStoreid() != null ) {
            storeDto.setStoreid( thestore.getStoreid() );
        }
        storeDto.setName( thestore.getName() );
        storeDto.setAddress( thestore.getAddress() );

        return storeDto;
    }

    @Override
    public store toStore(StoreDto storeDto) {
        if ( storeDto == null ) {
            return null;
        }

        store store = new store();

        store.setStoreid( storeDto.getStoreid() );
        store.setName( storeDto.getName() );
        store.setAddress( storeDto.getAddress() );

        return store;
    }
}
