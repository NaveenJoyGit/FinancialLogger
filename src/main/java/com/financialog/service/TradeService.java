package com.financialog.service;

import com.financialog.dto.CommonResponse;
import com.financialog.dto.TradeDetailsDto;
import com.financialog.dto.TradeRequestDto;
import com.financialog.entity.TradeDetails;
import com.financialog.repository.TradeDetailsRepository;
import com.financialog.security.AuthenticationFacade;
import com.financialog.util.ResponseGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradeService {

    public static final Logger logger = LoggerFactory.getLogger(TradeService.class);

    @Autowired
    TradeServiceFactory tradeServiceFactory;

    @Autowired
    ITradeService iTradeService;

    @Autowired
    TradeDetailsRepository tradeDetailsRepository;

    @Autowired
    AuthenticationFacade authenticationFacade;


    public CommonResponse<String> addTrade(TradeRequestDto tradeRequestDto) {
        CommonResponse<String> response = null;
        try {
            iTradeService = tradeServiceFactory.getTradeService(tradeRequestDto.getTradeType());
            iTradeService.addTrade(tradeRequestDto);
            response = ResponseGenerator.getSuccessResponse(null, "Successfully Saved Trade details");
        } catch (IllegalArgumentException ia) {
          logger.info("Trade Type service not yet implemented : {}", ia.getMessage());
          response = ResponseGenerator.getFailureResponse(null, "Cannot save " + tradeRequestDto.getTradeType().toString() + " Trades");
        } catch (Exception e) {
            logger.error("Exception occured while saving trade details. {}", e.getMessage());
            response = ResponseGenerator.getFailureResponse(null, "Failed to save trade details.");
        }
        return response;
    }

    public CommonResponse<List<TradeDetailsDto>> viewAllTrades() {
        try {
            List<TradeDetails> userTrades = tradeDetailsRepository.findByUser(authenticationFacade.getLoggedInUser());
            List<TradeDetailsDto> tradeDetailsDtoList = userTrades.stream().map(this::setTradeDetailsDto).collect(Collectors.toList());
            return ResponseGenerator.getSuccessResponse(tradeDetailsDtoList, "Successfully retrieved trade details");
        } catch (Exception e) {
            logger.info("Error Fetching trade details : {}", e.getMessage());
            return ResponseGenerator.getFailureResponse(null, "Failed to retrieve trade data");
        }
    }


    public TradeDetailsDto setTradeDetailsDto(TradeDetails tradeEntity) {
        return TradeDetailsDto.tradeDetailsBuilder()
                .setBuyPrice(tradeEntity.getBuyPrice().toString())
                .setCurrentPrice(tradeEntity.getStockId().getLastUpdatedPrice().toString())
                .setPercentageChange(ObjectUtils.isEmpty(tradeEntity.getPercentageChange()) ? "" : tradeEntity.getPercentageChange().toString())
                .setStockName(tradeEntity.getStockId().getName())
                .setTradeStatus(tradeEntity.getTradeStatus())
                .setTradeValue(tradeEntity.getTradeValue().toString());
    }

}
