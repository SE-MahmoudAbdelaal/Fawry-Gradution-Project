package com.codewithprojects.coupon.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
//import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumeRequest {

    @NotNull
    private String code ;
    private Long order_id;
    private Long customer_id;

}