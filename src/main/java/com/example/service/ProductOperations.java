package com.example.service;


import com.example.entity.Product;

import java.util.*;

public interface ProductOperations {
    void addProduct(Product product) throws Exception;
    Product getProduct(int id) throws Exception;
    void updateProduct(int id, String name, double price) throws Exception;
    void deleteProduct(int id) throws Exception;
    void displayAll();
    void sortByName();
    void sortByPrice();
}