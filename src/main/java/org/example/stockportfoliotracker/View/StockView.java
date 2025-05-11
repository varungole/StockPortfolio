package org.example.stockportfoliotracker.View;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.stockportfoliotracker.Model.Stock;

public class StockView {
    public final TextField nameInput = new TextField();
    public final TextField priceInput = new TextField();
    public final TextField symbolInput = new TextField();
    public final TextField marketCapInput = new TextField();
    public final Button addButton = new Button("Add Stock");
    public final TableView<Stock> tableView = new TableView<>();
    public final Button switchLightMode = new Button("Switch Dark Mode");
    private boolean isDarkMode;
    private VBox root;

    public VBox createLayout() {
        HBox inputBox = new HBox(10, nameInput, priceInput, symbolInput,marketCapInput, addButton, switchLightMode);
        root = new VBox(10,inputBox,tableView);
        root.setPadding(new Insets(10));
        isDarkMode = false;
        return root;
    }

    public void setupTable(ObservableList<Stock> stockData) {
        tableView.setItems(stockData);
        TableColumn<Stock,String> nameCol = new TableColumn<>("Stock Name");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().stockNameProperty());

        TableColumn<Stock,Number> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(cellData -> cellData.getValue().stockPriceProperty());

        TableColumn<Stock,String> tickerSymbolCol = new TableColumn<>("Symbol");
        tickerSymbolCol.setCellValueFactory(cellData -> cellData.getValue().tickerSymbolProperty());

        TableColumn<Stock,String> marketCapCol = new TableColumn<>("Market Cap");
        marketCapCol.setCellValueFactory(cellData -> cellData.getValue().marketCapitalizationProperty());

        TableColumn<Stock,String> deleteCol = new TableColumn<>("Delete");
        deleteCol.setCellFactory(param ->  new TableCell<>() {
            private final Button deleteButton = new Button("X");
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item,empty);
                if(empty || getTableRow().getItem() == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> {
                    Stock stock = getTableRow().getItem();
                    stockData.remove(stock);
                });
            }
        });
        tableView.getColumns().addAll(nameCol,priceCol,tickerSymbolCol,marketCapCol,deleteCol);
    }

    public void toggleMode() {
        isDarkMode = !isDarkMode;
        if(!isDarkMode) {
            root.setStyle("-fx-background-color:white; -fx-text-fill:black;");
            tableView.setStyle("-fx-control-inner-background:white; -fx-text-fill:black;");
            switchLightMode.setText("Switch Dark Mode");
        } else {
            root.setStyle("-fx-background-color:black; -fx-text-fill:white;");
            tableView.setStyle("-fx-control-inner-background:black; -fx-text-fill:white;");
            switchLightMode.setText("Switch Light Mode");
        }
    }

}
