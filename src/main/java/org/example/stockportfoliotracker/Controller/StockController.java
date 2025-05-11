package org.example.stockportfoliotracker.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.stockportfoliotracker.Model.Stock;
import org.example.stockportfoliotracker.View.StockView;

public class StockController {
    private final ObservableList<Stock> stockData = FXCollections.observableArrayList();
    private final StockView stockView;

    public StockController(StockView view) {
        this.stockView = view;
        setupEvents();
        view.setupTable(stockData);
    }

    private void setupEvents() {
        stockView.addButton.setOnAction(e -> insertRow());
        stockView.switchLightMode.setOnAction(e -> stockView.toggleMode());
    }

    private void insertRow() {
        try {
            String name = stockView.nameInput.getText();
            double price = Double.parseDouble(stockView.priceInput.getText());
            String symbol = stockView.symbolInput.getText();
            String marketCap = stockView.marketCapInput.getText();

            Stock stock = new Stock(name,price,symbol,marketCap);
            stockData.add(stock);

            stockView.nameInput.clear();
            stockView.priceInput.clear();
            stockView.symbolInput.clear();
            stockView.marketCapInput.clear();
        } catch (NumberFormatException exception) {
            System.out.println("Invalid number");
        }
    }

}
