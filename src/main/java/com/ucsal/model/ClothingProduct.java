package com.ucsal.model;

public class ClothingProduct extends Product {
    private String size;

    public ClothingProduct(String name, double price, int quantity, String category, String size) {
        super(name, price, quantity, category);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}