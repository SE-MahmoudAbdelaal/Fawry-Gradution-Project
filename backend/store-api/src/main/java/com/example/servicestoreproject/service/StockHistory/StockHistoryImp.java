package com.example.servicestoreproject.service.StockHistory;

import com.example.servicestoreproject.entity.Product;
import com.example.servicestoreproject.entity.StockHistory;
import com.example.servicestoreproject.repositry.StockHistoryRepo;
import com.example.servicestoreproject.service.FeignClient.ProductInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service

public class StockHistoryImp implements StockHistoryService {
    @Autowired
    private StockHistoryRepo stockHistoryRepo;
    @Autowired
    private ProductInterface productInterface;

    @Override
    @Transactional
    public void addStock(long productid, int amount) {

        Product product=productInterface.getProduct(productid);
        if(product.equals(null)){
            throw new NoSuchElementException(" no product with this id");
        }

        product.setStock_quantity(product.getStock_quantity()+amount);
        System.out.println(product.getStock_quantity());

        StockHistory stockHistory=new StockHistory() ;
        stockHistory.setProductId(productid);
        stockHistory.setType("add");
        stockHistory.setQuantityChange(amount);
        stockHistory.setTimeStamp(LocalDate.now());
        stockHistoryRepo.save(stockHistory);
        productInterface.updateProduct(product);

    }

    @Override
    @Transactional
    public void consumeStock(long productid, int amount) {
        Product product=productInterface.getProduct(productid);

        if(product.equals(null)){

            throw new NoSuchElementException(" no product with this id");
        }

        if(product.getStock_quantity()<amount){
            throw new NoSuchElementException("insufficient amount");
        }
        product.setStock_quantity(product.getStock_quantity()-amount);

        StockHistory stockHistory=new StockHistory() ;
        stockHistory.setProductId(productid);
        stockHistory.setType("consume");
        stockHistory.setQuantityChange(-amount);
        stockHistory.setTimeStamp(LocalDate.now());
        stockHistoryRepo.save(stockHistory);
        productInterface.updateProduct(product);


    }

    @Override
    public List<StockHistory> getAllStockHistories() {
        return stockHistoryRepo.findAll();

    }

    @Override
    public List<StockHistory> getStockHistoryByid(long id) {
        return stockHistoryRepo.findByProductId(id);
    }

    @Override
    public boolean checkAvailability(long id) {
        return productInterface.isavailable(id);

    }
}
