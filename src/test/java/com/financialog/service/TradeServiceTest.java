package com.financialog.service;

import com.financialog.dto.CommonResponse;
import com.financialog.dto.TradeRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TradeServiceTest {

    @InjectMocks
    TradeService tradeServiceMock;

    @Mock
    TradeServiceFactory tradeServiceFactoryMock;

    @Test
    void testGetTradeServiceImplementation() {
        given(tradeServiceFactoryMock.getTradeService(any())).willReturn(new SwingTradeService());
        CommonResponse<String> response = tradeServiceMock.addTrade(getMockTradeRequestDto());
        assertThat(response.getResponseCode()).isEqualTo(CommonResponse.ResponseCodeEnum.SUCCESS.getCode());
    }

    public TradeRequestDto getMockTradeRequestDto() {
        TradeRequestDto mockReq = new TradeRequestDto();
        mockReq.setStockName("TCS");
        mockReq.setBuyPrice(1000f);
        mockReq.setBuyQuantity(2);
        return mockReq;
    }

}