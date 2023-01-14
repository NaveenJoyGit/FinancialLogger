package com.financialog.dto;

public class TradeDetailsDto {
	private String stockName;
	private String buyPrice;
	private String currentPrice;
	private String profitOrLoss;
	private String tradeStatus;
	private String tradeValue;

	public static TradeDetailsDto tradeDetailsBuilder() {
		return new TradeDetailsDto();
	}

	public String getStockName() {
		return stockName;
	}

	public TradeDetailsDto setStockName(String stockName) {
		this.stockName = stockName;
		return this;
	}

	public String getBuyPrice() {
		return buyPrice;
	}

	public TradeDetailsDto setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
		return this;
	}

	public String getCurrentPrice() {
		return currentPrice;
	}

	public TradeDetailsDto setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
		return this;
	}

	public String getProfitOrLoss() {
		return profitOrLoss;
	}

	public TradeDetailsDto setProfitOrLoss(String profitOrLoss) {
		this.profitOrLoss = profitOrLoss;
		return this;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public TradeDetailsDto setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
		return this;
	}

	public String getTradeValue() {
		return tradeValue;
	}

	public TradeDetailsDto setTradeValue(String tradeValue) {
		this.tradeValue = tradeValue;
		return this;
	}
}
