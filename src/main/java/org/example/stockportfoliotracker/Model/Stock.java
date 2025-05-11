package org.example.stockportfoliotracker.Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Stock {

    public StringProperty stockName;
    public DoubleProperty stockPrice;
    public StringProperty tickerSymbol;
    public StringProperty marketCapitalization;

    public Stock(String stockName, double stockPrice, String tickerSymbol,String marketCapitalization) {
        this.stockName = new SimpleStringProperty(stockName);
        this.stockPrice = new SimpleDoubleProperty(stockPrice);
        this.tickerSymbol = new SimpleStringProperty(tickerSymbol);
        this.marketCapitalization = new SimpleStringProperty(marketCapitalization);
    }

    public String getStockName() {
        return stockName.get();
    }

    public StringProperty stockNameProperty() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName.set(stockName);
    }

    public double getStockPrice() {
        return stockPrice.get();
    }

    public DoubleProperty stockPriceProperty() {
        return stockPrice;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice.set(stockPrice);
    }

    public String getTickerSymbol() {
        return tickerSymbol.get();
    }

    public StringProperty tickerSymbolProperty() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol.set(tickerSymbol);
    }

    public String getMarketCapitalization() {
        return marketCapitalization.get();
    }

    public StringProperty marketCapitalizationProperty() {
        return marketCapitalization;
    }

    public void setMarketCapitalization(String marketCapitalization) {
        this.marketCapitalization.set(marketCapitalization);
    }
}
