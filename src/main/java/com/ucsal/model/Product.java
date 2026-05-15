package com.ucsal.model;

public abstract class Product {
    protected final long id;
    protected String name;
    protected double price;
    protected int quantity;

    // TODO tirar esse negocio
    protected String category;

    private static long nextId = 1;

    public Product(String name, double price, int quantity, String category) {
        this.id = nextId++;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + name + " | Categoria: " + category + " | Preço: " + price + " | Quantidade: " + quantity;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Product product = (Product) obj;

        return id == product.id;
    }
}