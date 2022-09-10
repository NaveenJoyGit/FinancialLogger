package com.financialog.service;

import com.financialog.dto.StockData;
import com.financialog.entity.Stock;
import com.financialog.repository.StockRepository;
import com.financialog.util.StockSubject;
import com.financialog.util.StockUtils;
import com.financialog.util.TradePriceSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class StockService {

    private StockSubject stockSubject;
    private TradePriceSubscriber tradePriceSubscriber;
    private StockRepository stockRepository;
    private NseDataService nseDataService;


    public StockService(StockSubject stockSubject,
                        TradePriceSubscriber tradePriceSubscriber,
                        StockRepository stockRepository,
                        NseDataService nseDataService) {
        this.stockSubject = stockSubject;
        this.tradePriceSubscriber = tradePriceSubscriber;
        this.stockRepository = stockRepository;
        this.nseDataService = nseDataService;
    }

    @Transactional
    public Stock saveNewStockDetails(String stockCode) {
        StockData stockData = nseDataService.getNseStockData(stockCode)
                .getResponseData();
        Stock stock = new Stock();
        stock.setCode(stockData.getCode());
        stock.setName(stockData.getStockName());
        double totalTradedValue = StockUtils.getTotalTradedValueAsDouble(stockData.getMetadata().get("totalTradedVolume").textValue());
        double tradedCapital = totalTradedValue * StockUtils.getTotalTradedValueAsDouble(stockData.getCurrentPrice());
        String sectorByMarketCap = StockUtils.getSectorByMarketCap(tradedCapital);
        stock.setCategory(sectorByMarketCap);
        Double cmp=StockUtils.getTotalTradedValueAsDouble(stockData.getCurrentPrice());
        stockSubject.updateStockPrice(stock, cmp.floatValue());
        stock.setLastUpdatedPrice(stockSubject, cmp.floatValue());
        Stock savedStock = stockRepository.save(stock);
        return savedStock;
    }

}
