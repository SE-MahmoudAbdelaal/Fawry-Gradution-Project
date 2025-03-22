package com.e_commerce.order_api.utilitie;

import com.e_commerce.order_api.entity.Item;

import java.math.BigDecimal;
import java.util.List;

public class OrderUtilities {
    public static double countTotalPrice(List<Item> cart){
        double total = 0;
        for(int i = 0; i < cart.size(); i++){
            total = total + (Double) cart.get(i).getSubTotal();
        }
        return total;
    }
    public static double countTotalPriceWithCoupon(List<Item> cart , String value_type , double value){
      double total =  countTotalPrice( cart );
      if(value_type.equals("fixed")){
          if(total>value){
              total = total-value;
          }else{
              total = 0;
          }
      }else{
         total =total - (total * value)/100;
      }
        return total;
    }
}