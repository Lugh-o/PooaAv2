package com.ucsal.persistence;

import com.ucsal.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stock implements StockRepository {
    private static final Stock INSTANCE = new Stock();
    private final List<Product> products = new ArrayList<>();

    private Stock() {}

    public static Stock getInstance() {
        return INSTANCE;
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public void removeProduct(Product product) {
        products.remove(product);
    }

    @Override
    public Product findById(long id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    @Override
    public boolean isEmpty() {
        return products.isEmpty();
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
}