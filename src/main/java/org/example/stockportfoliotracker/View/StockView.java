package org.example.stockportfoliotracker.View;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.stockportfoliotracker.Model.Stock;

import java.net.URL;

import static org.example.stockportfoliotracker.Util.*;


public class StockView {


    //CSS Resource paths
    private final TextField nameInput         = new TextField();
    private final TextField priceInput        = new TextField();
    private final TextField symbolInput       = new TextField();
    private final TextField marketCapInput    = new TextField();

    private final Button addButton             = new Button("Add Stock");
    private final Button showPieChartButton    = new Button("Show Pie Chart");
    private final Button switchLightMode       = new Button("Switch Dark Mode");

    private final TableView<Stock> tableView   = new TableView<>();
    private final Label errorLabel             = new Label();

    private VBox root;
    private Scene scene;
    private boolean isDarkMode;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public TextField getNameInput() {return this.nameInput;}
    public TextField getPriceInput() {return this.priceInput;}
    public TextField getSymbolInput() {return this.symbolInput;}
    public TextField getMarketCapInput() {return this.marketCapInput;}
    public Button getAddButton() {return this.addButton;}
    public Button getShowPieChartButton() {return this.showPieChartButton;}
    public Button getSwitchLightMode() {return this.switchLightMode;}
    public Label getErrorLabel() {return this.errorLabel;}


    private <T>TableColumn<Stock,T> makeColumn(String title, Callback<TableColumn.CellDataFeatures<Stock,T>, ObservableValue<T>> callback) {
        TableColumn<Stock,T> col = new TableColumn<>(title);
        col.setCellValueFactory(callback);
        return col;
    }

    public void setupTable(ObservableList<Stock> stockData) {
        tableView.setItems(stockData);
        for(Stock stock : stockData) {
            System.out.println(stock.stockName);
        }
        tableView.getColumns().addAll(
                this.<String>makeColumn("Name",cellData -> cellData.getValue().stockNameProperty()),
                this.<Number>makeColumn("Price",cellData -> cellData.getValue().stockPriceProperty()),
                this.<String> makeColumn("Symbol",cellData -> cellData.getValue().tickerSymbolProperty()),
                this.<String>makeColumn("Market Cap",cellData -> cellData.getValue().marketCapitalizationProperty()),
                makeDeleteColumn()
        );
    }

    private TableColumn<Stock,Void> makeDeleteColumn() {
        TableColumn<Stock,Void> deleteCol = new TableColumn<Stock,Void>("Delete");
        deleteCol.setCellFactory(table -> new TableCell<>() {
            private final Button deleteBtn = new Button("X");
            {
                deleteBtn.setOnAction(e -> {
                    // Remove the Stock for this row:
                    Stock s = getTableView().getItems().get(getIndex());
                    getTableView().getItems().remove(s);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : deleteBtn);
            }
        });
        return deleteCol;
    }

    public VBox createLayout() {
        HBox inputBox = new HBox(10, nameInput, priceInput, symbolInput,marketCapInput, addButton, switchLightMode);
        errorLabel.getStyleClass().add(ERROR_LABEL);
        root = new VBox(10,inputBox,errorLabel,tableView,showPieChartButton);
        root.setPadding(new Insets(10));
        isDarkMode = false;
        return root;
    }

    public void toggleMode() {
        isDarkMode = !isDarkMode;
        String sheet = isDarkMode ? LIGHT_CSS : DARK_CSS;
        URL cssUrl = getClass().getResource(sheet);
        if (cssUrl == null) {
            throw new IllegalStateException("Missing stylesheet: " + sheet);
        }
        scene.getStylesheets().setAll(cssUrl.toExternalForm());
        switchLightMode.setText(isDarkMode ? "Switch Light Mode" : "Switch Dark Mode");
    }

    public void displayPieChart(ObservableList<PieChart.Data> slices) {
        PieChart pieChart = new PieChart(slices);
        pieChart.setTitle("Portfolio allocation");
        pieChart.setClockwise(true);
        VBox chartLayout = new VBox(pieChart);
        chartLayout.setPadding(new Insets(10));
        Stage chartStage = new Stage();
        chartStage.setTitle("Portfolio Pie Chart");
        chartStage.setScene(new Scene(chartLayout,500,400));
        chartStage.show();
    }
}
