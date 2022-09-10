package com.financialog.util;

import com.financialog.entity.Stock;
import com.financialog.entity.TradeDetails;
import com.financialog.repository.TradeDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TradePriceSubscriber implements EventListener<Stock>{

    @Autowired
    TradeDetailsRepository tradeDetailsRepository;

    @Override
    public void update(Stock stock) {
        List<TradeDetails> trades = tradeDetailsRepository.findByStockCode(stock.getCode());
        trades.forEach(trade -> updateTradeWithNewPrice(trade, stock.getLastUpdatedPrice()));
        tradeDetailsRepository.saveAll(trades);
    }

    public void updateTradeWithNewPrice(TradeDetails tradeDetails, Float stockPrice) {
        tradeDetails.setTradeValue(stockPrice * tradeDetails.getQuantity());
        tradeDetails.setPercentageChange(StockUtils.getPercentageChange(stockPrice * tradeDetails.getQuantity(),
                tradeDetails.getBuyPrice() * tradeDetails.getQuantity()));
    }


}
