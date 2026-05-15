package com.ucsal.command;

import com.ucsal.annotation.CommandHandler;
import com.ucsal.model.Product;
import com.ucsal.persistence.Stock;
import com.ucsal.persistence.StockRepository;

@CommandHandler("remove-product")
public class RemoveProductCommand implements Command {
    private final StockRepository stock;
    private final Product removedProduct;

    public RemoveProductCommand(Stock stock, Product product) {
        this.stock = stock;
        this.removedProduct = product;
    }

    @Override
    public void execute() {
        stock.removeProduct(removedProduct);
    }

    @Override
    public void undo() {
        stock.addProduct(removedProduct);
    }

    @Override
    public String getDescription() {
        return "Removeu " + removedProduct.getName();
    }
}