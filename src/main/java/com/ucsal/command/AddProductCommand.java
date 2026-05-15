package com.ucsal.command;

import com.ucsal.annotation.CommandHandler;
import com.ucsal.model.Product;
import com.ucsal.persistence.StockRepository;

@CommandHandler("add-product")
public class AddProductCommand implements Command {
    private final StockRepository stock;
    private final Product product;

    public AddProductCommand(StockRepository stock, Product product) {
        this.stock = stock;
        this.product = product;
    }

    @Override
    public void execute() {
        stock.addProduct(product);
    }

    @Override
    public void undo() {
        stock.removeProduct(product);
    }

    @Override
    public String getDescription() {
        return "Adicionou " + product.getName();
    }
}