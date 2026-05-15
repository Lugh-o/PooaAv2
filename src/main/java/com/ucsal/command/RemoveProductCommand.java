package com.ucsal.command;

import com.ucsal.model.Product;
import com.ucsal.persistence.Stock;

public class RemoveProductCommand implements Command {

    private final Product removedProduct;

    public RemoveProductCommand(Stock stock, Product product) {
        this.removedProduct = product;
    }

    @Override
    public void execute() {
        Stock.getInstance().removeProduct(removedProduct);
    }

    @Override
    public void undo() {
        Stock.getInstance().addProduct(removedProduct);
    }

    @Override
    public String getDescription() {
        return "Removeu " + removedProduct.getName();
    }
}