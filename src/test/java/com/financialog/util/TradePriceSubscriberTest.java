package com.financialog.util;

import com.financialog.entity.Stock;
import com.financialog.entity.TradeDetails;
import com.financialog.repository.TradeDetailsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TradePriceSubscriberTest {

    @InjectMocks
    TradePriceSubscriber tradePriceSubscriberMock;

    @Mock
    TradeDetailsRepository tradeDetailsRepositoryMock;

    @Mock
    StockSubject stockSubjectMock;

    @Test
    void testUpdatePercentageChangeOfEachStock() {
        Mockito.when(tradeDetailsRepositoryMock.findByStockCode(Mockito.anyString()))
                .thenReturn(getAllTradesMock());
        assertDoesNotThrow(() -> tradePriceSubscriberMock.update(getStockMock()));
    }

    private List<TradeDetails> getAllTradesMock() {
        List<TradeDetails> allTrades = new ArrayList<>();
        TradeDetails t1 = new TradeDetails();
        t1.setTradeValue(2400f);
        t1.setPercentageOfCapital(6.25f);
        t1.setPercentageChange(+20f);
        t1.setBuyPrice(1200f);
        t1.setQuantity(2);
        allTrades.add(t1);
        TradeDetails t2 = new TradeDetails();
        t2.setTradeValue(1600f);
        t2.setPercentageOfCapital(31.25f);
        t2.setPercentageChange(-20f);
        t2.setBuyPrice(800f);
        t2.setQuantity(2);
        allTrades.add(t2);
        TradeDetails t3 = new TradeDetails();
        t3.setTradeValue(2800f);
        t2.setPercentageOfCapital(62.5f);
        t3.setPercentageChange(4f);
        t3.setBuyPrice(1400f);
        t3.setQuantity(2);
        allTrades.add(t3);

        return allTrades;
    }

    public Stock getStockMock() {
        Stock mockStock = new Stock();
        mockStock.setLastUpdatedPrice(stockSubjectMock, 1500f);
        return mockStock;
    }

}