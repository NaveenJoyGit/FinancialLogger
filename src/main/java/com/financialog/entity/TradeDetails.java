package com.financialog.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Entity
public class TradeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tradeDetailsId;
    @OneToOne
    @JoinColumn(name = "stock_id", referencedColumnName = "stockId")
    private Stock stockId;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private FinancialLoggerUser user;
    private Integer quantity;
    private String tradeStatus;
    private String tradeType;
    private Float buyPrice;
    private Float tradeValue;
    private String tradeDescription;
    private Float profitOrLoss;
    private Float percentageChange;
    private Float percentageOfCapital;
    private Date buyDate;
    private Date sellDate;
    private Boolean isShortSell;
    private Date expiryDate;

    public Long getTradeDetailsId() {
        return tradeDetailsId;
    }

    public void setTradeDetailsId(Long tradeDetailsId) {
        this.tradeDetailsId = tradeDetailsId;
    }

    public Stock getStockId() {
        return stockId;
    }

    public void setStockId(Stock stockId) {
        this.stockId = stockId;
    }

    public FinancialLoggerUser getUser() {
        return user;
    }

    public void setUser(FinancialLoggerUser user) {
        this.user = user;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public Float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Float getTradeValue() {
        return tradeValue;
    }

    public void setTradeValue(Float tradeValue) {
        this.tradeValue = tradeValue;
    }

    public String getTradeDescription() {
        return tradeDescription;
    }

    public void setTradeDescription(String tradeDescription) {
        this.tradeDescription = tradeDescription;
    }

    public Float getProfitOrLoss() {
        return profitOrLoss;
    }

    public void setProfitOrLoss(Float profitOrLoss) {
        this.profitOrLoss = profitOrLoss;
    }

    public Float getPercentageChange() {
        return percentageChange;
    }

    public void setPercentageChange(Float percentageChange) {
        this.percentageChange = percentageChange;
    }

    public Float getPercentageOfCapital() {
        return percentageOfCapital;
    }

    public void setPercentageOfCapital(Float percentageOfCapital) {
        this.percentageOfCapital = percentageOfCapital;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public Date getSellDate() {
        return sellDate;
    }

    public void setSellDate(Date sellDate) {
        this.sellDate = sellDate;
    }

    public Boolean getShortSell() {
        return isShortSell;
    }

    public void setShortSell(Boolean shortSell) {
        isShortSell = shortSell;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
