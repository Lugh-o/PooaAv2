package com.ucsal.ui;

import com.ucsal.command.*;
import com.ucsal.factory.CommandFactory;
import com.ucsal.model.ClothingProduct;
import com.ucsal.model.FoodProduct;
import com.ucsal.model.Product;
import com.ucsal.persistence.Stock;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TerminalUI {
    private final Scanner scanner;
    private final Invoker invoker;
    private final Stock stock;
    private final CommandFactory factory;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TerminalUI(Scanner scanner, Invoker invoker, Stock stock, CommandFactory factory) {
        this.scanner = scanner;
        this.invoker = invoker;
        this.stock = stock;
        this.factory = factory;
    }

    public void start() {
        int option = -1;

        while (option != 0) {
            printMenu();
            try {
                option = Integer.parseInt(scanner.nextLine());
                handleOption(option);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Digite um número.");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- CONTROLE DE ESTOQUE ---");
        System.out.println("1. Listar Produtos");
        System.out.println("2. Adicionar Produto");
        System.out.println("3. Remover Produto");
        System.out.println("4. Atualizar Quantidade");
        System.out.println("5. Desfazer Última Ação");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    // TODO melhorar isso aqui
    private void handleOption(int option) {
        switch (option) {
            case 1:
                stock.listProducts();
                break;
            case 2:
                addProduct();
                break;
            case 3:
                removeProduct();
                break;
            case 4:
                updateQuantity();
                break;
            case 5:
                invoker.undo();
                break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void addProduct() {
        System.out.println("Tipo de produto (1. Comida, 2. Vestuário): ");
        int type = Integer.parseInt(scanner.nextLine());

        System.out.print("Nome: ");
        String name = scanner.nextLine();
        System.out.print("Preço: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Quantidade: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.print("Categoria: ");
        String category = scanner.nextLine();

        Product product;
        if (type == 1) {
            System.out.print("Data de validade (dd/mm/aaaa): ");
            LocalDate date = LocalDate.parse(scanner.nextLine(), dateFormatter);
            product = new FoodProduct(name, price, quantity, category, date);
        } else {
            System.out.print("Tamanho: ");
            String size = scanner.nextLine();
            product = new ClothingProduct(name, price, quantity, category, size);
        }
        Command command = factory.create("add-product", stock, product);
        invoker.executeCommand(command);
        System.out.println("Produto adicionado com sucesso.");
    }

    private void removeProduct() {
        if (stock.isEmpty()) {
            System.out.println("Estoque vazio.");
            return;
        }
        stock.listProducts();
        System.out.print("ID do produto a remover: ");
        long id = Long.parseLong(scanner.nextLine());
        Product product = stock.findById(id);

        if (product != null) {
            Command command = factory.create("remove-product", stock, product);
            invoker.executeCommand(command);
            System.out.println("Produto removido.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void updateQuantity() {
        if (stock.isEmpty()) {
            System.out.println("Estoque vazio.");
            return;
        }
        stock.listProducts();
        System.out.print("ID do produto: ");
        long id = Long.parseLong(scanner.nextLine());
        Product product = stock.findById(id);

        if (product != null) {
            System.out.print("Nova quantidade: ");
            int newQuantity = Integer.parseInt(scanner.nextLine());
            Command command = factory.create("update-product", product, newQuantity);
            invoker.executeCommand(command);
            System.out.println("Quantidade atualizada.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
}
