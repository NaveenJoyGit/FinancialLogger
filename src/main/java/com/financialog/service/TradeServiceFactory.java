package com.financialog.service;

import com.financialog.enums.TradeTypeEnum;
import org.springframework.stereotype.Service;

@Service
public class TradeServiceFactory {

    public ITradeService getTradeService(String tradeType) {
        return TradeTypeEnum.valueOf(tradeType).getService();
    }

}
