package com.financialog.service;

import com.financialog.enums.TradeTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class TradeServiceFactory {


    @Autowired
    ApplicationContext context;

    public ITradeService getTradeService(String tradeType) {
        return TradeTypeEnum.valueOf(tradeType).getService(context);
    }

}
