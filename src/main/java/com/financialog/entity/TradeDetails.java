package com.financialog.entity;

import javax.persistence.*;

@Entity
public class TradeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tradeDetailsId;

    @OneToOne
    @JoinColumn(name = "trade_id", referencedColumnName = "tradeId")
    private TradeMaster tradeId;
    @OneToOne
    @JoinColumn(name = "stock_id", referencedColumnName = "stockId")
    private Stock stockId;

}
