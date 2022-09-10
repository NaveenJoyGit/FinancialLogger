package com.financialog.service;

import com.financialog.dto.CommonResponse;
import com.financialog.dto.StockData;
import com.financialog.providers.ApiParameter;
import com.financialog.providers.StockDataFetch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NseDataServiceTest {

    @InjectMocks
    NseDataService nseDataServiceMock;

    @Mock
    StockDataFetch stockDataFetch;

    public static final String STOCK_NAME = "TCS";
    public static final String STOCK_PRICE = "1000";


    @Test
    void testFetchNseDataDoesNotThrowError() {
        String symbol = "TCS";
        given(stockDataFetch.fetchStockData(any(ApiParameter.class))).willReturn(getMockStockData());
        CommonResponse<StockData> nseStockData = nseDataServiceMock.getNseStockData(symbol);
        assertThat(nseStockData.getResponseCode()).isEqualTo(CommonResponse.ResponseCodeEnum.SUCCESS.getCode());
        assertDoesNotThrow(() -> nseDataServiceMock.getNseStockData(symbol));
    }

    public StockData getMockStockData() {
        StockData data = new StockData();
        data.setStockName(STOCK_NAME);
        data.setCurrentPrice(STOCK_PRICE);
        return data;
    }

}