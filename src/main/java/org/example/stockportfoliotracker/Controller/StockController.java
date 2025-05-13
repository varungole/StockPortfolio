package org.example.stockportfoliotracker.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextField;
import org.example.stockportfoliotracker.Model.Stock;
import org.example.stockportfoliotracker.View.StockView;

import java.util.List;

public class StockController {
    private final ObservableList<Stock> stockData = FXCollections.observableArrayList();
    private final StockView stockView;
    private final List<TextField> inputs;

    public StockController(StockView view) {
        this.stockView = view;
        this.inputs = List.of(view.getNameInput(), view.getPriceInput(), view.getSymbolInput(), view.getMarketCapInput());
        setupInputListeners();
        setupEvents();
        view.setupTable(stockData);
    }

    private void setupEvents() {
        stockView.getAddButton().setOnAction(e -> insertRow());
        stockView.getSwitchLightMode().setOnAction(e -> stockView.toggleMode());
        stockView.getShowPieChartButton().setOnAction(e -> showPieChart());
    }

    private void showPieChart() {
        ObservableList<PieChart.Data> slices = FXCollections.observableArrayList();
        for(Stock s : stockData) {
            slices.add(new PieChart.Data(s.getTickerSymbol(), s.getStockPrice()));
        }
        stockView.displayPieChart(slices);
    }

    private void setupInputListeners() {
        Runnable updater = this::updateAddButtonState;
        for(TextField tf : inputs) {
            tf.textProperty().addListener((obs, oldVal, newVal) -> updater.run());
        }
        updateAddButtonState();
    }

    private boolean isFormValid() {
        boolean allFilled = inputs.stream().allMatch(textField -> !textField.getText().trim().isEmpty());
        if(!allFilled) return false;

        try {
            Double.parseDouble(stockView.getPriceInput().getText().trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void updateAddButtonState() {
        boolean isValid = isFormValid();
        stockView.getAddButton().setDisable(!isValid);
        if(isValid) stockView.getErrorLabel().setText("");
    }

    private void insertRow() {
        if(!isFormValid()) {
            stockView.getErrorLabel().setText("Please fill all fields and enter a valid price");
        }

        String name = stockView.getNameInput().getText().trim();
        double price = Double.parseDouble(stockView.getPriceInput().getText().trim());
        String symbol = stockView.getSymbolInput().getText().trim();
        String marketCap = stockView.getMarketCapInput().getText().trim();

        stockData.add(new Stock(name,price,symbol,marketCap));

        inputs.forEach(TextField::clear);
        updateAddButtonState();
    }

}
