package com.su.order.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.su.order.order.entity.Order;
import com.su.order.product.entity.Category;
import com.su.order.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Helper {
    @Autowired
    private static ObjectMapper objectMapper;

    public static String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
    public static Order makeOrder(Long id){
        return new Order( "이메일", "주소",1000L);
    }
    public static Product makeProduct(String productName){
        return new Product(productName, Category.COFFEE,1000L,1000,"테스트 상품0",getTime(),getTime());
    }
    public static LocalDateTime getTime(){
        return LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }


}
