package com.financialog.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TradeServiceFactoryTest {

    @InjectMocks
    TradeServiceFactory tradeServiceFactoryMock;

    @Test
    void testgetStockServiceFactoryMethod() {
        ITradeService tradeService = tradeServiceFactoryMock.getTradeService("SWING");
        assertThat(tradeService).isInstanceOf(SwingTradeService.class);
    }

}