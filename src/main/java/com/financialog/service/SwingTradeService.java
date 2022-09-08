package com.financialog.service;

import com.financialog.dto.StockData;
import com.financialog.dto.TradeRequestDto;
import com.financialog.entity.FinancialLoggerUser;
import com.financialog.entity.Stock;
import com.financialog.entity.TradeDetails;
import com.financialog.enums.HoldStatus;
import com.financialog.enums.TradeTypeEnum;
import com.financialog.repository.StockRepository;
import com.financialog.repository.TradeDetailsRepository;
import com.financialog.security.AuthenticationFacade;
import com.financialog.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class SwingTradeService implements ITradeService {

    public static final Logger logger = LoggerFactory.getLogger(SwingTradeService.class);

    private TradeDetailsRepository tradeDetailsRepository;
    private StockRepository stockRepository;
    private NseDataService nseDataService;
    private CapitalEmployedSubject capitalEmployedSubject;
    private CapitalEmployedSubscriber capitalEmployedSubscriber;
    private StockSubject stockSubject;
    private TradePriceSubscriber tradePriceSubscriber;
    private StockService stockService;
    private AuthenticationFacade authenticationFacade;

    @Autowired
    public SwingTradeService(TradeDetailsRepository tradeDetailsRepository,
                             StockRepository stockRepository,
                             NseDataService nseDataService,
                             CapitalEmployedSubject capitalEmployedSubject,
                             CapitalEmployedSubscriber capitalEmployedSubscriber,
                             StockSubject stockSubject,
                             TradePriceSubscriber tradePriceSubscriber,
                             StockService stockService,
                             AuthenticationFacade authenticationFacade) {
        this.tradeDetailsRepository = tradeDetailsRepository;
        this.stockRepository = stockRepository;
        this.nseDataService = nseDataService;
        this.capitalEmployedSubject = capitalEmployedSubject;
        this.capitalEmployedSubscriber = capitalEmployedSubscriber;
        this.capitalEmployedSubject.subscribe(this.capitalEmployedSubscriber);
        this.stockSubject = stockSubject;
        this.tradePriceSubscriber = tradePriceSubscriber;
        this.stockSubject.subscribe(tradePriceSubscriber);
        this.stockService = stockService;
        this.authenticationFacade = authenticationFacade;
    }

    public SwingTradeService() {
    }

    @Override
    public void addTrade(TradeRequestDto tradeRequestDto) {
        TradeDetails tradeDetails = new TradeDetails();
        try {
            FinancialLoggerUser currentUser = authenticationFacade.getLoggedInUser();
            Stock stock = fetchOrAddStockDetails(tradeRequestDto.getStockName());
            float tradeValue = tradeRequestDto.getBuyQuantity() * tradeRequestDto.getBuyPrice();
            capitalEmployedSubject.updateCapital(tradeDetails, tradeValue);
            tradeDetails.setStockId(stock);
            tradeDetails.setUser(currentUser);
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
        tradeDetails.setQuantity(tradeRequestDto.getBuyQuantity());
        tradeDetails.setBuyPrice(tradeRequestDto.getBuyPrice());
        tradeDetails.setTradeStatus(HoldStatus.ACTIVE.name());
        tradeDetails.setTradeType(TradeTypeEnum.SWING.name());
        tradeDetails.setShortSell(Boolean.FALSE);
        tradeDetails.setTradeDescription(tradeRequestDto.getTradeDescription());
        tradeDetails.setBuyDate(new Date());
        tradeDetailsRepository.save(tradeDetails);
    }

    private Stock fetchOrAddStockDetails(String stockCode) {
        return stockRepository.findByCode(stockCode)
                .or(() -> Optional.ofNullable(stockService.saveNewStockDetails(stockCode)))
                .orElseThrow(() -> new RuntimeException("Stock Saving Failed"));
    }

    @Override
    public void getTradeDetails() {
        //TODO: Code to get Details of trade already logged by the user
    }

    @Override
    public void completeTrade() {
        //TODO: Code to mark as complete the trades entered by the user
    }
}
