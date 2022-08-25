package com.financialog.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TradeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tradeDetailsId;
    @OneToOne
    @JoinColumn(name = "stock_id", referencedColumnName = "stockId")
    private Stock stockId;
    private Integer quantity;
    private String tradeStatus;
    private String tradeType;
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
