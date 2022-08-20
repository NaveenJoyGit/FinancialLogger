package com.financialog.service;

import com.financialog.dto.TradeRequestDto;

public interface ITradeService {

    void addTrade(TradeRequestDto tradeRequestDto);

    void getTradeDetails();

}
