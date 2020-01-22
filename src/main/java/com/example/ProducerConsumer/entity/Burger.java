package com.example.ProducerConsumer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Burger extends FastFood {
    enum BurgerSize {
        SMALL,
        MEDIUM,
        LARGE
    }
    private BurgerSize size;
}
