package com.example.servicestoreproject.controller;

import com.example.servicestoreproject.dto.StoreDto;
import com.example.servicestoreproject.entity.store;
import com.example.servicestoreproject.service.store.StoreService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
@Tag(name="store service")
public class StoreController {

    private final StoreService storeService;
    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/addstore")
    @Operation(summary = "  create a  store")
    public ResponseEntity<String> createStore(@RequestBody StoreDto thestore){
        System.out.println(thestore.getAddress());
        storeService.addStore(thestore);
        return new ResponseEntity<>("store added successfully", HttpStatus.CREATED);
    }
    @GetMapping("/getstore/{id}")
    @Operation(summary = "  get a certain store")
    public ResponseEntity<StoreDto> getStore(@PathVariable long id){
        return new ResponseEntity<>(storeService.getStore(id),HttpStatus.OK);
    }

    @GetMapping("/getall")
    @Operation(summary = "  get a list of stores")
    public ResponseEntity<List<StoreDto>> getall(){
        return new ResponseEntity<>(storeService.getAllStores(),HttpStatus.OK);
    }

    @DeleteMapping("/deletestore/{id}")
    @Operation(summary = "  delete a certain store")
    public ResponseEntity<String> deleteStore(@PathVariable long id){
        storeService.deleteStore(id);
        return new ResponseEntity<>("store deleted successfully",HttpStatus.OK);
    }

    @PutMapping("/updatestore/{id}")
    @Operation(summary = "  update a certain store")
    public ResponseEntity<String> updateStore(@PathVariable long id,@RequestBody StoreDto storeDto){
        storeService.updateStore(id, storeDto);
        return new ResponseEntity<>("store updated successfully",HttpStatus.OK);
    }



}
