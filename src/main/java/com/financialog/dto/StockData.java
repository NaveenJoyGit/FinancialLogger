package com.financialog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class StockData {

    private String code;
    private String stockName;
    private String currentPrice;

    public StockData(String code, String stockName, String currentPrice) {
        this.code = code;
        this.stockName = stockName;
        this.currentPrice = currentPrice;
    }

    public StockData() {}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

}
