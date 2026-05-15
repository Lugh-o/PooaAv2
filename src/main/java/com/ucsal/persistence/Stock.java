package com.ucsal.persistence;

import com.ucsal.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Stock {
    private static final Stock INSTANCE = new Stock();
    private final List<Product> products = new ArrayList<>();

    private Stock() {}

    public static Stock getInstance() {
        return INSTANCE;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }

        return null;
    }

    public void listProducts() {
        if (products.isEmpty()) {
            System.out.println("Estoque vazio.");
            return;
        }

        for (Product product : products) {
            System.out.println(product);
        }
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }
}