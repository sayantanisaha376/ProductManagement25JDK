package com.example.service.impl;

import com.example.entity.Product;
import com.example.service.ProductOperations;

import java.util.*;

public class ProductService2 implements ProductOperations {
    private TreeMap<Integer, Product> productMap = new TreeMap<>();

    @Override
    public void addProduct(Product product) throws Exception {
        if (productMap.containsKey(product.getId())) {
            throw new Exception("Product with ID " + product.getId() + " already exists!");
        }
        productMap.put(product.getId(), product);
    }

    @Override
    public Product getProduct(int id) throws Exception {
        if (!productMap.containsKey(id)) {
            throw new Exception("Product with ID " + id + " not found!");
        }
        return productMap.get(id);
    }

    @Override
    public void updateProduct(int id, String name, double price) throws Exception {
        if (!productMap.containsKey(id)) {
            throw new Exception("Cannot update! Product with ID " + id + " not found!");
        }
        Product p = productMap.get(id);
        p.setName(name);
        p.setPrice(price);
    }

    @Override
    public void deleteProduct(int id) throws Exception {
        if (!productMap.containsKey(id)) {
            throw new Exception("Cannot delete! Product with ID " + id + " not found!");
        }
        productMap.remove(id);
    }

    @Override
      public void displayAll() {
        if (productMap.isEmpty()) {
            IO.println("No products available!");
        } else {
            IO.println("Products displayed in ascending ID order:");
            productMap.forEach((id, p) -> IO.println(p.toString()));
        }
    }

    @Override
    public void sortByName() {
        List<Product> list = new ArrayList<>(productMap.values());
        list.sort(Comparator.comparing(Product::getName));
        IO.println("Products sorted by Name:");
        list.forEach(p -> IO.println(p.toString()));
    }

    @Override
    public void sortByPrice() {
        List<Product> list = new ArrayList<>(productMap.values());
        list.sort(Comparator.comparingDouble(Product::getPrice));
        IO.println("Products sorted by Price:");
        list.forEach(p -> IO.println(p.toString()));
    }

    public Map<Integer, Product> productMap() {
        return productMap;
    }
}
