package com.financialog.util;

import com.financialog.enums.StockConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class StockUtilsTest {

    @InjectMocks
    StockUtils stockUtils;

    @ParameterizedTest
    @ValueSource(doubles = {9_000_000_000d, 4_500_000_000d })
    @DisplayName("Check Large Cap stocks will return LARGE_CAP as sector")
    void testGetLargeCapByMarketCap(double markCaps) {
        String sectorByMarketCap = StockUtils.getSectorByMarketCap(markCaps);
        assertThat(sectorByMarketCap)
                .isEqualTo(StockConstants.LARGE_CAP.name());
    }

    @ParameterizedTest
    @ValueSource(doubles = {9_000_000_0d, 8_500_000_0d })
    @DisplayName("Check Large Cap stocks will return LARGE_CAP as sector")
    void testGetMidCapByMarketCap(double markCaps) {
        String sectorByMarketCap = StockUtils.getSectorByMarketCap(markCaps);
        assertThat(sectorByMarketCap)
                .isEqualTo(StockConstants.MID_CAP.name());
    }

    @ParameterizedTest
    @ValueSource(strings = {"50,16,244", "50,16,245.00", "3,223.95"})
    void testGetTotalTradedValueAsLong(String values) {
        double totalTradedValueAsLong = StockUtils.getTotalTradedValueAsDouble(values);
        assertThat(totalTradedValueAsLong).isInstanceOf(Double.class).isIn(5016244d, 5016245.00d, 3223.95d);
    }

}