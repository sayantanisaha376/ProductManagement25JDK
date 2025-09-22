package com.example.entity;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    private int id;
    private String name;
    private double price;

    @Override
    public String toString() {
        return "Product[ID=%d, Name=%s, Price=%.2f]".formatted(id, name, price);
    }
}