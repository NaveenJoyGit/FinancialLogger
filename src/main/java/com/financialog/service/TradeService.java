package com.financialog.service;

import com.financialog.dto.CommonResponse;
import com.financialog.dto.TradeRequestDto;
import com.financialog.util.ResponseGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TradeService {

    public static final Logger logger = LoggerFactory.getLogger(TradeService.class);

    @Autowired
    TradeServiceFactory tradeServiceFactory;

    public CommonResponse<String> addTrade(TradeRequestDto tradeRequestDto) {
        CommonResponse<String> response = null;
        try {
            ITradeService tradeService = tradeServiceFactory.getTradeService(tradeRequestDto.getTradeType());
            tradeService.addTrade(tradeRequestDto);
            response = ResponseGenerator.getSuccessResponse(null, "Successfully Saved Trade details");
        } catch (Exception e) {
            logger.error("Exception occured while saving trade details. {}", e.getMessage());
            response = ResponseGenerator.getFailureResponse(null, "Failed to save trade details.");
        }
        return response;
    }

}
