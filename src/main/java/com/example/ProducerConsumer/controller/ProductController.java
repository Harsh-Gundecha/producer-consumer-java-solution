package com.example.ProducerConsumer.controller;

import com.example.ProducerConsumer.entity.Burger;
import com.example.ProducerConsumer.entity.FastFood;
import com.example.ProducerConsumer.entity.Pizza;
import com.example.ProducerConsumer.resource.OrderQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class ProductController {

    @Autowired
    private OrderQueue orderQueue;

    private void setId(FastFood fastFood){
        fastFood.setId((long)(Math.random() * Long.MAX_VALUE));
    }

    @PostMapping("/burger")
    ResponseEntity<Boolean> postOrder(@RequestBody Burger burger){
        setId(burger);
        return ResponseEntity.ok(orderQueue.getOrderQueue().add(burger));
    }

    @PostMapping("/pizza")
    ResponseEntity<Boolean> postOrder(@RequestBody Pizza pizza){
        setId(pizza);
        return ResponseEntity.ok(orderQueue.getOrderQueue().add(pizza));
    }

    @GetMapping
    ResponseEntity<?> getOrder(){
        if(orderQueue.getOrderQueue().isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Actor Not Found");
        return ResponseEntity.ok(orderQueue.getOrderQueue().remove());
    }

//    @GetMapping("/dummy")
//    ResponseEntity<FastFood> NoBodyGonnaCallMe(){
//        FastFood fastFood = Burger.builder().size("2").build();
//        return ResponseEntity.ok(fastFood);
//    }
}
