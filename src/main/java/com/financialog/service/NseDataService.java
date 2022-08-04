package com.financialog.service;

import com.financialog.dto.StockData;
import com.financialog.providers.QuoteApiParameter;
import com.financialog.providers.StockDataFetch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class NseDataService {

    @Autowired
    @Qualifier("nseDataFetch")
    StockDataFetch stockDataFetch;


    public StockData getNseStockData(String symbol) {
        return stockDataFetch.fetchStockData(new QuoteApiParameter(Arrays.asList(symbol)));
    }

}
