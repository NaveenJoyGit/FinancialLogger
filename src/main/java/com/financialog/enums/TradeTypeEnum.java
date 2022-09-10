package com.financialog.enums;

import com.financialog.service.ITradeService;
import com.financialog.service.SwingTradeService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public enum TradeTypeEnum {

    SWING{
        @Override
        public ITradeService getService(ApplicationContext context) {
            return context.getBean(SwingTradeService.class);
        }
    };

    public abstract ITradeService getService(ApplicationContext context);

}
