package com.ucsal.persistence;

import com.ucsal.model.Product;

import java.util.List;

public interface StockRepository {
    void addProduct(Product product);
    void removeProduct(Product product);
    Product findById(long id);
    List<Product> getProducts();
    boolean isEmpty();
}
