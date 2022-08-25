package com.financialog.service;

import com.financialog.dto.CommonResponse;
import com.financialog.dto.StockData;
import com.financialog.dto.TradeRequestDto;
import com.financialog.entity.Stock;
import com.financialog.entity.TradeDetails;
import com.financialog.enums.HoldStatus;
import com.financialog.enums.TradeTypeEnum;
import com.financialog.repository.StockRepository;
import com.financialog.repository.TradeDetailsRepository;
import com.financialog.util.StockUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SwingTradeService implements ITradeService {

    private TradeDetailsRepository tradeDetailsRepository;
    private StockRepository stockRepository;
    private NseDataService nseDataService;

    @Autowired
    public SwingTradeService(TradeDetailsRepository tradeDetailsRepository,
                             StockRepository stockRepository,
                             NseDataService nseDataService) {
        this.tradeDetailsRepository = tradeDetailsRepository;
        this.stockRepository = stockRepository;
        this.nseDataService = nseDataService;
    }

    public SwingTradeService() {
    }

    @Override
    public void addTrade(TradeRequestDto tradeRequestDto) {
        TradeDetails tradeDetails = new TradeDetails();
        tradeDetails.setQuantity(tradeRequestDto.getBuyQuantity());
        tradeDetails.setTradeStatus(HoldStatus.ACTIVE.name());
        tradeDetails.setTradeType(TradeTypeEnum.SWING.name());
        tradeDetails.setShortSell(Boolean.FALSE);
        tradeDetails.setBuyDate(new Date());
        Stock stock = fetchOrAddStockDetails(tradeRequestDto.getStockName());
        tradeDetails.setStockId(stock);
        tradeDetailsRepository.save(tradeDetails);
    }

    private Stock fetchOrAddStockDetails(String stockCode) {
        Stock stock = stockRepository.findByName(stockCode).or(() -> saveStockAndReturn(stockCode)).orElseThrow(() -> new RuntimeException("Stock Saving Failed"));
        return stock;
    }

    private Optional<Stock> saveStockAndReturn(String stockCode) {
        StockData stockData = nseDataService.getNseStockData(stockCode)
                .getResponseData();
        Stock stock = new Stock();
        stock.setCode(stockData.getCode());
        stock.setName(stockData.getStockName());
        double totalTradedValue = StockUtils.getTotalTradedValueAsLong(stockData.getMetadata().get("totalTradedVolume").textValue());
        double tradedCapital = totalTradedValue * Long.parseLong(stockData.getCurrentPrice());
        String sectorByMarketCap = StockUtils.getSectorByMarketCap(tradedCapital);
        stock.setCategory(sectorByMarketCap);
        Stock savedStock = stockRepository.save(stock);
        return Optional.ofNullable(savedStock);
    }

    @Override
    public void getTradeDetails() {

    }
}
