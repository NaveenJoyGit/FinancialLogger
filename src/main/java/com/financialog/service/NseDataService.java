package com.financialog.service;

import com.financialog.dto.CommonResponse;
import com.financialog.dto.StockData;
import com.financialog.providers.QuoteApiParameter;
import com.financialog.providers.StockDataFetch;
import com.financialog.util.ResponseGenerator;
import com.financialog.util.StockSubject;
import com.financialog.util.TradePriceSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class NseDataService {

    public static final Logger logger = LoggerFactory.getLogger(NseDataService.class);

    @Autowired
    @Qualifier("nseDataFetch")
    StockDataFetch stockDataFetch;

    public CommonResponse<StockData> getNseStockData(String symbol) {
        try {
            StockData stockData = stockDataFetch.fetchStockData(new QuoteApiParameter(Arrays.asList(symbol)));
            String successMessage = "Successfully retrieved stock data";

            //TODO: Update all trades with the new stock price
            logger.info(successMessage);
            return ResponseGenerator.getSuccessResponse(stockData, successMessage);
        } catch (Exception e) {
            logger.error("Exception occurred for stock {}", symbol);
            return ResponseGenerator.getFailureResponse(null, "Exception occurred" + e.getMessage());
        }
    }


}
