package com.financialog.service;

import com.financialog.dto.TradeRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface ITradeService {

    void addTrade(TradeRequestDto tradeRequestDto);

    void getTradeDetails();

}
