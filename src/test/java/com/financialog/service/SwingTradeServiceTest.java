package com.financialog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financialog.dto.CommonResponse;
import com.financialog.dto.StockData;
import com.financialog.dto.TradeRequestDto;
import com.financialog.entity.Stock;
import com.financialog.enums.TradeTypeEnum;
import com.financialog.repository.StockRepository;
import com.financialog.repository.TradeDetailsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SwingTradeServiceTest {

    @InjectMocks
    SwingTradeService swingTradeServiceMock;

    @Mock
    StockRepository stockRepositoryMock;

    @Mock
    NseDataService nseDataServiceMock;

    @Mock
    TradeDetailsRepository tradeDetailsRepositoryMock;


    @Test
    void testAddTradeWithStockNameAlreadyPresent() {
        when(stockRepositoryMock.findByName(getTradeRequestDto().getStockName())).thenReturn(Optional.of(new Stock()));
        assertDoesNotThrow(() -> swingTradeServiceMock.addTrade(getTradeRequestDto()));
    }

    @Test
    void testAddTradeWithNoStockNamePresent() throws JsonProcessingException {
        when(stockRepositoryMock.findByName(getTradeRequestDto().getStockName())).thenReturn(Optional.ofNullable(null));
        CommonResponse<StockData> commonResponse = new CommonResponse<>(null, null, getStockDataDto());
        when(nseDataServiceMock.getNseStockData(getTradeRequestDto().getStockName()))
                .thenReturn(commonResponse);
        when(stockRepositoryMock.save(any(Stock.class))).thenReturn(new Stock());
        assertDoesNotThrow(() -> swingTradeServiceMock.addTrade(getTradeRequestDto()));
    }

    private StockData getStockDataDto() throws JsonProcessingException {
        StockData stockData = new StockData();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode metadata = mapper.readTree(getMetadataString());
        stockData.setStockName("TCS").setCurrentPrice("2000").setMetadata(metadata);
        return stockData;
    }

    public  String getMetadataString() {
        return "{\n" +
                "    \"pricebandupper\": \"969.10\",\n" +
                "    \"symbol\": \"SUNPHARMA\",\n" +
                "    \"applicableMargin\": \"14.01\",\n" +
                "    \"bcEndDate\": \"-\",\n" +
                "    \"totalSellQuantity\": \"287\",\n" +
                "    \"adhocMargin\": \"-\",\n" +
                "    \"companyName\": \"Sun Pharmaceutical Industries Limited\",\n" +
                "    \"marketType\": \"N\",\n" +
                "    \"exDate\": \"19-AUG-22\",\n" +
                "    \"bcStartDate\": \"-\",\n" +
                "    \"css_status_desc\": \"Listed\",\n" +
                "    \"dayHigh\": \"890.65\",\n" +
                "    \"basePrice\": \"881.00\",\n" +
                "    \"securityVar\": \"10.51\",\n" +
                "    \"pricebandlower\": \"792.90\",\n" +
                "    \"sellQuantity5\": \"-\",\n" +
                "    \"sellQuantity4\": \"-\",\n" +
                "    \"sellQuantity3\": \"-\",\n" +
                "    \"cm_adj_high_dt\": \"29-APR-22\",\n" +
                "    \"sellQuantity2\": \"-\",\n" +
                "    \"dayLow\": \"875.00\",\n" +
                "    \"sellQuantity1\": \"287\",\n" +
                "    \"quantityTraded\": \"50,16,244\",\n" +
                "    \"pChange\": \"-0.48\",\n" +
                "    \"totalTradedValue\": \"44,260.83\",\n" +
                "    \"deliveryToTradedQuantity\": \"75.81\",\n" +
                "    \"totalBuyQuantity\": \"-\",\n" +
                "    \"averagePrice\": \"882.35\",\n" +
                "    \"indexVar\": \"-\",\n" +
                "    \"cm_ffm\": \"94,722.15\",\n" +
                "    \"purpose\": \"DIVIDEND - RS 3 PER SHARE\",\n" +
                "    \"buyPrice2\": \"-\",\n" +
                "    \"secDate\": \"25-Aug-2022 00:00:00\",\n" +
                "    \"buyPrice1\": \"-\",\n" +
                "    \"high52\": \"967.05\",\n" +
                "    \"previousClose\": \"881.00\",\n" +
                "    \"ndEndDate\": \"-\",\n" +
                "    \"low52\": \"733.70\",\n" +
                "    \"buyPrice4\": \"-\",\n" +
                "    \"buyPrice3\": \"-\",\n" +
                "    \"recordDate\": \"22-AUG-22\",\n" +
                "    \"deliveryQuantity\": \"38,02,713\",\n" +
                "    \"buyPrice5\": \"-\",\n" +
                "    \"priceBand\": \"No Band\",\n" +
                "    \"extremeLossMargin\": \"3.50\",\n" +
                "    \"cm_adj_low_dt\": \"06-DEC-21\",\n" +
                "    \"varMargin\": \"10.51\",\n" +
                "    \"sellPrice1\": \"877.30\",\n" +
                "    \"sellPrice2\": \"-\",\n" +
                "    \"totalTradedVolume\": \"50,16,244\",\n" +
                "    \"sellPrice3\": \"-\",\n" +
                "    \"sellPrice4\": \"-\",\n" +
                "    \"sellPrice5\": \"-\",\n" +
                "    \"change\": \"-4.20\",\n" +
                "    \"surv_indicator\": \"-\",\n" +
                "    \"ndStartDate\": \"-\",\n" +
                "    \"buyQuantity4\": \"-\",\n" +
                "    \"isExDateFlag\": false,\n" +
                "    \"buyQuantity3\": \"-\",\n" +
                "    \"buyQuantity2\": \"-\",\n" +
                "    \"buyQuantity1\": \"-\",\n" +
                "    \"series\": \"EQ\",\n" +
                "    \"faceValue\": \"1.00\",\n" +
                "    \"buyQuantity5\": \"-\",\n" +
                "    \"closePrice\": \"877.30\",\n" +
                "    \"open\": \"888.00\",\n" +
                "    \"isinCode\": \"INE044A01036\",\n" +
                "    \"lastPrice\": \"876.80\"\n" +
                "}";
    }

    public TradeRequestDto getTradeRequestDto() {
        return new TradeRequestDto("TCS", 3, 100.2f,
                TradeTypeEnum.valueOf("SWING").name(), "200MA");
    }

}