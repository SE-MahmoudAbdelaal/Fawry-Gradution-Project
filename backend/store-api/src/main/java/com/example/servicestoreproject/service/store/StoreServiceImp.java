package com.example.servicestoreproject.service.store;

import com.example.servicestoreproject.dto.StoreDto;
import com.example.servicestoreproject.entity.store;
import com.example.servicestoreproject.mapper.StoreMapper;
import com.example.servicestoreproject.repositry.StoreRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
@NoArgsConstructor(force = true)
@Service
public class StoreServiceImp implements StoreService{
    private final StoreRepo storeRepo;
   private final StoreMapper storeMapper;
    @Autowired
    public StoreServiceImp(StoreRepo storeRepo,StoreMapper storeMapper) {
        this.storeRepo = storeRepo;
        this.storeMapper = storeMapper;
    }

    @Override
    public void addStore(StoreDto storeDto) {
        store thestore=storeMapper.toStore(storeDto);
        System.out.println(thestore.getName());
        //store thestore=new store(storeDto.getStore_id(),storeDto.getName(),storeDto.getAddress());
        System.out.println(thestore.getName());
        storeRepo.save(thestore);
    }

    @Override
    public StoreDto getStore(long id) {
        storeRepo.findById(id).orElseThrow( ()->new NoSuchElementException("no store with this id"));
        store thestore=storeRepo.findById(id).get();
        return storeMapper.toStoreDto(thestore);

    }

    @Override
    public List<StoreDto> getAllStores() {
        return storeRepo.findAll().stream().map(storeMapper::toStoreDto).collect(Collectors.toList());

    }

    @Override
    public void deleteStore(long id) {
        storeRepo.findById(id).orElseThrow(()->new NoSuchElementException("no store with this id"));

        storeRepo.deleteById(id);
    }

    @Override
    public void updateStore(long id, StoreDto storeDto) {
        store thestore=storeRepo.findById(id).orElseThrow(()->new NoSuchElementException("no store with this id"));
       store Store=storeMapper.toStore(storeDto);
       if(storeDto.getName()!=null){
           thestore.setName(storeDto.getName());

       }

        if(storeDto.getAddress()!=null){
            thestore.setName(storeDto.getAddress());

        }

        storeRepo.save(thestore);


    }
}
