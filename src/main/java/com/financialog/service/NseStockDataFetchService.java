package com.financialog.service;

import com.financialog.dto.StockData;
import com.financialog.providers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.http.HttpResponse;
import java.util.Arrays;

/**
 * Implementation class that provides stock data fetching service specific to National Stoc Exchange
 */
@Component
@Qualifier("nseDataFetch")
public class NseStockDataFetchService implements StockDataFetch {

    public static final Logger logger = LoggerFactory.getLogger(NseStockDataFetchService.class);

    @Autowired
    FinLogHttpClient client;

    @Autowired
    @Qualifier("stockDataParser")
    JsonDeserializer<StockData> stockDataParser;

    @Override
    public StockData fetchStockData(ApiParameter... params) {
        logger.info("Inside NseStockDataFetch Service.");
        NseHttpClient nseClient = new NseHttpClient();

        /* Functionally uses getStockQuoteData method of NseHttpClient */
        HttpResponse<String> responseObj = client.sendRequest(nseClient::getStockQuoteData, Arrays.asList(params));
        return stockDataParser.parseObject(responseObj.body());
    }
}
