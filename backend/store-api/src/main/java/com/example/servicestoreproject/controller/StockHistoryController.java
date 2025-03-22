package com.example.servicestoreproject.controller;

import com.example.servicestoreproject.dto.StoreDto;
import com.example.servicestoreproject.entity.StockHistory;
import com.example.servicestoreproject.service.FeignClient.ProductInterface;
import com.example.servicestoreproject.service.StockHistory.StockHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/stock")
@Tag(name="stock history service")
public class StockHistoryController {
    @Autowired
    private ProductInterface productInterface;
    private final StockHistoryService stockHistoryService;
    @Autowired
    public StockHistoryController(StockHistoryService stockHistoryService) {
        this.stockHistoryService = stockHistoryService;
    }

    @PostMapping("/add/{id}")
    @Operation(summary = "  add stock")
    public ResponseEntity<String> addStock(@RequestParam int amount, @PathVariable long id){
        stockHistoryService.addStock(id,amount);

        return new ResponseEntity<>("stock added successfully", HttpStatus.CREATED);
    }

    @PostMapping("/consume/{id}")
    @Operation(summary = "  consume stock")
    public ResponseEntity<String> consumeStock(@RequestParam int amount, @PathVariable long id){
        if(productInterface.getProduct(id).getStock_quantity()<amount){
            return new ResponseEntity<>("amount not enough", HttpStatus.BAD_REQUEST);
        }
        else {
            stockHistoryService.consumeStock(id, amount);
            return new ResponseEntity<>("stock consumed successfully", HttpStatus.CREATED);
        }
    }
    @GetMapping("/getallstockhistorires")
    @Operation(summary = "get all stock histories")
    public ResponseEntity<List<StockHistory>> getAll(){
        return new ResponseEntity<>(stockHistoryService.getAllStockHistories(),HttpStatus.OK);
    }

    @GetMapping("{id}/getallstockhistorires")
    @Operation(summary = "get all stock histories for a certain product")
    public ResponseEntity<List<StockHistory>> getAllStock(@PathVariable long id){
        return new ResponseEntity<>(stockHistoryService.getStockHistoryByid(id),HttpStatus.OK);
    }
    @GetMapping("/isavailable/{id}")
    @Operation(summary = "check if product is available or not")
    public ResponseEntity<Boolean> isavailable(@PathVariable long id){
        return new ResponseEntity<>(stockHistoryService.checkAvailability(id),HttpStatus.OK);
    }

}
