package com.financialog.util;

import com.financialog.entity.TradeDetails;
import com.financialog.enums.HoldStatus;
import com.financialog.repository.TradeDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CapitalEmployedSubscriber implements EventListener<Float> {

    public static final Logger logger = LoggerFactory.getLogger(CapitalEmployedSubscriber.class);

    @Autowired
    TradeDetailsRepository tradeDetailsRepository;

    @Override
    public void update(Float newCapital) {
        logger.info("Updating Percentage of capital employed for all trades based on new Capital : {}", newCapital);
        List<TradeDetails> allTrades = tradeDetailsRepository.findByTradeStatus(HoldStatus.ACTIVE.name());
        allTrades.forEach(tradeDetails ->
                tradeDetails.setPercentageOfCapital(tradeDetails.getTradeValue() * 100 / newCapital));
        tradeDetailsRepository.saveAll(allTrades);
    }
}
