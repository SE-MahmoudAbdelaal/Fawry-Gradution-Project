package com.example.product_api.controller;

import com.example.product_api.ProductService.ProductService;
import com.example.product_api.dto.ProductDto;
import com.example.product_api.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductRestController {

  @Autowired
  ProductService productService ;
  //@Autowired

  @GetMapping("")
  public ResponseEntity <Page<ProductDto>> getProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){

    return new ResponseEntity<>(productService.getAllProducts(page,size),HttpStatus.OK);
  }


  @GetMapping("/user/{userid}")
  public ResponseEntity <Page<ProductDto>> getProductsforuser(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,@PathVariable Long userid){

    return new ResponseEntity<>(productService.getAllProductsforuser(page,size,userid),HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity <ProductDto> getProduct(@PathVariable("id") int id){

    return new ResponseEntity<>(productService.getProductbyid(id),HttpStatus.OK) ;
  }

  @GetMapping("/{userid}/{productid}")
  public ResponseEntity <ProductDto> getProduct(@PathVariable("userid") Long userid,@PathVariable("productid") int productid){
    return new ResponseEntity<>(productService.getProductbyidforuser(productid,userid),HttpStatus.OK) ;
  }

  @GetMapping("/getproductbyname/{name}")
  public ResponseEntity <List<ProductDto>> getProductbyname(@PathVariable("name") String name){

    return new ResponseEntity<>(productService.findProductByname(name),HttpStatus.OK);
  }
  @GetMapping("/{id}/isavailable")
  public ResponseEntity <Boolean> isavailable(@PathVariable("id") int id){
    return new ResponseEntity<>(productService.isAvailable(id),HttpStatus.OK);
  }

  @PostMapping("")
  public ResponseEntity <ProductDto> createProduct(@RequestBody ProductDto productdto){
   ProductDto theproductdto= productService.addProduct(productdto);
    return new ResponseEntity<>(theproductdto,HttpStatus.OK);
  }
  @PostMapping("addproduct/{userid}")
  public ResponseEntity <ProductDto> createProduct(@RequestBody ProductDto productdto,@PathVariable Long userid){
    ProductDto theproductdto= productService.addProductforuser(productdto,userid);
    return new ResponseEntity<>(theproductdto,HttpStatus.OK);
  }

//  @PutMapping("")
//  public ResponseEntity <ProductDto> updateProduct(@RequestBody ProductDto productdto){
//
//    return new ResponseEntity<>(productService.updateProduct(productdto),HttpStatus.OK);
//  }

  @DeleteMapping("/{id}")
  public ResponseEntity <String> deleteProduct(@PathVariable("id") int id){
    productService.deleteproductbyid(id);
    return  new ResponseEntity<>("product deleted successfully",HttpStatus.NO_CONTENT);
  }

  @PutMapping("/updateproduct/{id}")
  public ResponseEntity <ProductDto> updateProducts(@RequestBody ProductDto productdto,@PathVariable int id){

    return new ResponseEntity<>(productService.updateProducts(productdto,id),HttpStatus.OK);
  }

}
