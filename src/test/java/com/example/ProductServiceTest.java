package com.example;



import static org.junit.jupiter.api.Assertions.*;

import com.example.entity.Product;
import com.example.service.impl.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductServiceTest {
    private ProductService service;

    @BeforeEach
    void setUp() {
        service = new ProductService();
    }

    @Test
    void testAddProductSuccess() throws Exception {
        Product p = new Product(1, "Laptop", 55000);
        service.addProduct(p);
        assertEquals(p, service.getProduct(1));
    }

    @Test
    void testAddProductDuplicate() throws Exception {
        Product p = new Product(1, "Laptop", 55000);
        service.addProduct(p);
        Exception ex = assertThrows(Exception.class, () -> {
            service.addProduct(new Product(1, "Mouse", 500));
        });
        assertEquals("Product with ID 1 already exists!", ex.getMessage());
    }

    @Test
    void testGetProductSuccess() throws Exception {
        Product p = new Product(2, "Mouse", 500);
        service.addProduct(p);
        Product fetched = service.getProduct(2);
        assertEquals("Mouse", fetched.getName());
        assertEquals(500, fetched.getPrice());
    }

    @Test
    void testGetProductNotFound() {
        Exception ex = assertThrows(Exception.class, () -> {
            service.getProduct(99);
        });
        assertEquals("Product with ID 99 not found!", ex.getMessage());
    }

    @Test
    void testUpdateProductSuccess() throws Exception {
        Product p = new Product(3, "Keyboard", 1500);
        service.addProduct(p);
        service.updateProduct(3, "Mechanical Keyboard", 2500);
        Product updated = service.getProduct(3);
        assertEquals("Mechanical Keyboard", updated.getName());
        assertEquals(2500, updated.getPrice());
    }

    @Test
    void testUpdateProductNotFound() {
        Exception ex = assertThrows(Exception.class, () -> {
            service.updateProduct(88, "Tablet", 12000);
        });
        assertEquals("Cannot update! Product with ID 88 not found!", ex.getMessage());
    }

    @Test
    void testDeleteProductSuccess() throws Exception {
        Product p = new Product(4, "Monitor", 10000);
        service.addProduct(p);
        service.deleteProduct(4);
        Exception ex = assertThrows(Exception.class, () -> {
            service.getProduct(4);
        });
        assertEquals("Product with ID 4 not found!", ex.getMessage());
    }

    @Test
    void testDeleteProductNotFound() {
        Exception ex = assertThrows(Exception.class, () -> {
            service.deleteProduct(77);
        });
        assertEquals("Cannot delete! Product with ID 77 not found!", ex.getMessage());
    }

    @Test
    void testSortByName() throws Exception {
        service.addProduct(new Product(10, "Laptop", 55000));
        service.addProduct(new Product(11, "Mouse", 500));
        service.addProduct(new Product(12, "Keyboard", 1500));
        service.sortByName();
        var list = new java.util.ArrayList<>(service.productMap().values());
        list.sort(java.util.Comparator.comparing(Product::getName));
        assertEquals("Keyboard", list.get(0).getName());
        assertEquals("Laptop", list.get(1).getName());
        assertEquals("Mouse", list.get(2).getName());
    }

    @Test
    void testSortByPrice() throws Exception {
        service.addProduct(new Product(20, "Laptop", 55000));
        service.addProduct(new Product(21, "Mouse", 500));
        service.addProduct(new Product(22, "Keyboard", 1500));
        service.sortByPrice();
        var list = new java.util.ArrayList<>(service.productMap().values());
        list.sort(java.util.Comparator.comparingDouble(Product::getPrice));
        assertEquals("Mouse", list.get(0).getName());
        assertEquals("Keyboard", list.get(1).getName());
        assertEquals("Laptop", list.get(2).getName());
    }
}
