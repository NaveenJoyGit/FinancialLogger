package com.financialog.service;

import com.financialog.dto.CommonResponse;
import com.financialog.dto.TradeRequestDto;
import com.financialog.util.ResponseGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

    public static final Logger logger = LoggerFactory.getLogger(TradeService.class);

    @Autowired
    TradeServiceFactory tradeServiceFactory;

    @Autowired
    ITradeService iTradeService;


    public CommonResponse<String> addTrade(TradeRequestDto tradeRequestDto) {
        CommonResponse<String> response = null;
        try {
            iTradeService = tradeServiceFactory.getTradeService(tradeRequestDto.getTradeType());
            iTradeService.addTrade(tradeRequestDto);
            response = ResponseGenerator.getSuccessResponse(null, "Successfully Saved Trade details");
        } catch (Exception e) {
            logger.error("Exception occured while saving trade details. {}", e.getMessage());
            response = ResponseGenerator.getFailureResponse(null, "Failed to save trade details.");
        }
        return response;
    }

}
