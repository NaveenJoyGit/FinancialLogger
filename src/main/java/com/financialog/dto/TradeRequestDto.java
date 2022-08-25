package com.financialog.dto;

public class TradeRequestDto {
    private String stockName;
    private Integer buyQuantity;
    private Float buyPrice;
    private String tradeType;
    private String tradeDescription;


    public TradeRequestDto() {
    }

    public TradeRequestDto(String stockName, Integer buyQuantity, Float buyPrice, String tradeType, String tradeDescription) {
        this.stockName = stockName;
        this.buyQuantity = buyQuantity;
        this.buyPrice = buyPrice;
        this.tradeType = tradeType;
        this.tradeDescription = tradeDescription;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Integer getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(Integer buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public Float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeDescription() {
        return tradeDescription;
    }

    public void setTradeDescription(String tradeDescription) {
        this.tradeDescription = tradeDescription;
    }
}
