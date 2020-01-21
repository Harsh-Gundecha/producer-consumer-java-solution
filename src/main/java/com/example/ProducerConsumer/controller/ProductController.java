package com.example.ProducerConsumer.controller;

import com.example.ProducerConsumer.entity.Burger;
import com.example.ProducerConsumer.entity.FastFood;
import com.example.ProducerConsumer.entity.Pizza;
import com.example.ProducerConsumer.service.ProcessFastFood;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class ProductController {

    @Autowired
    private ProcessFastFood processFastFood;

    private void setId(FastFood fastFood){
        Random random = new Random();
        long id = random.nextLong();
        while(id<0)
            id = random.nextLong();
        fastFood.setId(id);
    }

    @PostMapping("/burger")
    ResponseEntity<Boolean> postOrder(@RequestBody Burger burger){
        setId(burger);
        return ResponseEntity.ok(processFastFood.addOrder(burger));
    }

    @PostMapping("/pizza")
    ResponseEntity<Boolean> postOrder(@RequestBody Pizza pizza){
        setId(pizza);
        return ResponseEntity.ok(processFastFood.addOrder(pizza));
    }

    @GetMapping
    ResponseEntity<?> getOrder(){
        Object fastFood = processFastFood.getOrder();
        if(fastFood == null)
            return (ResponseEntity<?>) ResponseEntity.notFound();
        return ResponseEntity.ok(fastFood);
    }

    @GetMapping("/dummy")
    ResponseEntity<FastFood> NoBodyGonnaCallMe(){
        FastFood fastFood = Burger.builder().size("2").build();
        return ResponseEntity.ok(fastFood);
    }
}
