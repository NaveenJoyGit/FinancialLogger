package com.financialog.enums;

import com.financialog.service.ITradeService;
import com.financialog.service.SwingTradeService;

public enum TradeTypeEnum {
    SWING{
        @Override
        public ITradeService getService() {
            return new SwingTradeService();
        }
    };

    public abstract ITradeService getService();

}
