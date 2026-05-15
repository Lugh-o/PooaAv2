package com.ucsal.command;

import com.ucsal.model.Product;
import com.ucsal.persistence.Stock;

public class AddProductCommand implements Command {

    private final Product product;

    public AddProductCommand(Product product) {
        this.product = product;
    }

    @Override
    public void execute() {
        Stock.getInstance().addProduct(product);
    }

    @Override
    public void undo() {
        Stock.getInstance().removeProduct(product);
    }

    @Override
    public String getDescription() {
        return "Adicionou " + product.getName();
    }
}