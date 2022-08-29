package com.financialog.util;

import com.financialog.entity.TradeDetails;
import com.financialog.repository.TradeDetailsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CapitalEmployedSubscriberTest {

    @InjectMocks
    CapitalEmployedSubscriber capitalEmployedSubscriber;

    @Mock
    TradeDetailsRepository tradeDetailsRepositoryMock;

    @Test
    void testUpdateCapitalEmployedForEachTrade() {
        when(tradeDetailsRepositoryMock.findAll()).thenReturn(getAllTradesMock());
        assertDoesNotThrow(() -> capitalEmployedSubscriber.update(2000f));
    }

    private List<TradeDetails> getAllTradesMock() {
        List<TradeDetails> allTrades = new ArrayList<>();
        TradeDetails t1 = new TradeDetails();
        t1.setTradeValue(100f);
        t1.setPercentageOfCapital(6.25f);
        allTrades.add(t1);
        TradeDetails t2 = new TradeDetails();
        t2.setTradeValue(500f);
        t2.setPercentageOfCapital(31.25f);
        allTrades.add(t2);
        TradeDetails t3 = new TradeDetails();
        t3.setTradeValue(1000f);
        t2.setPercentageOfCapital(62.5f);
        allTrades.add(t3);

        return allTrades;
    }

}