package com.ucsal.command;

import com.ucsal.annotation.CommandHandler;
import com.ucsal.model.Product;

// TODO ignorewarnings
@CommandHandler("update-product")
public class UpdateQuantityCommand implements Command {
    private final Product product;
    private final int newQuantity;
    private int oldQuantity;

    public UpdateQuantityCommand(Product product, int newQuantity) {
        this.product = product;
        this.newQuantity = newQuantity;
    }

    @Override
    public void execute() {
        oldQuantity = product.getQuantity();

        product.setQuantity(newQuantity);
    }

    @Override
    public void undo() {
        product.setQuantity(oldQuantity);
    }

    @Override
    public String getDescription() {
        return "Atualizou quantidade de " +
                product.getName() +
                " para " +
                newQuantity;
    }
}