package com.financialog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

public class StockData {

    private String code;
    private String stockName;
    private String currentPrice;

    private JsonNode metadata;

    public StockData(String code, String stockName, String currentPrice) {
        this.code = code;
        this.stockName = stockName;
        this.currentPrice = currentPrice;
    }

    public StockData() {}

    public String getCode() {
        return code;
    }

    public StockData setCode(String code) {
        this.code = code;
        return this;
    }

    public String getStockName() {
        return stockName;
    }

    public StockData setStockName(String stockName) {
        this.stockName = stockName;
        return this;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public StockData setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
        return this;
    }

    public JsonNode getMetadata() {
        return metadata;
    }

    public StockData setMetadata(JsonNode metadata) {
        this.metadata = metadata;
        return this;
    }

}
