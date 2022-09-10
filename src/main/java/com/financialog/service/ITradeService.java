package com.financialog.service;

import com.financialog.dto.TradeDetailsDto;
import com.financialog.dto.TradeRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITradeService {

    void addTrade(TradeRequestDto tradeRequestDto);

    List<TradeDetailsDto> getTradeDetails();

    void completeTrade();

}
