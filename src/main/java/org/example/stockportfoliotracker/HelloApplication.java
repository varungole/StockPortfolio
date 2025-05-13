package org.example.stockportfoliotracker;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.stockportfoliotracker.Controller.StockController;
import org.example.stockportfoliotracker.View.StockView;


import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        StockView view = new StockView();
        new StockController(view);

        Scene scene = new Scene(view.createLayout(), 1000, 600);
        stage.setScene(scene);
        view.setScene(scene);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/light.css")).toExternalForm());
        stage.setTitle("Stock Portfolio Tracker");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}