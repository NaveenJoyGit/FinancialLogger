package com.financialog.service;

import com.financialog.enums.TradeTypeEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TradeServiceFactoryTest {

    @InjectMocks
    TradeServiceFactory tradeServiceFactoryMock;

    @Mock
    ApplicationContext context;

    @Test
    void testgetStockServiceFactoryMethod() {
        when(context.getBean(SwingTradeService.class)).thenReturn(new SwingTradeService());
        ITradeService tradeService = tradeServiceFactoryMock.getTradeService("SWING");
        assertThat(tradeService).isInstanceOf(SwingTradeService.class);
    }

}