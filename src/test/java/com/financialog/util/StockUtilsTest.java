package com.financialog.util;

import com.financialog.enums.StockConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StockUtilsTest {

    @InjectMocks
    StockUtils stockUtils;

    @ParameterizedTest
    @ValueSource(longs = {9_000_000_000l, 4_500_000_000l })
    @DisplayName("Check Large Cap stocks will return LARGE_CAP as sector")
    void testGetLargeCapByMarketCap(long markCaps) {
        String sectorByMarketCap = StockUtils.getSectorByMarketCap(markCaps);
        assertThat(sectorByMarketCap)
                .isEqualTo(StockConstants.LARGE_CAP.name());
    }

    @ParameterizedTest
    @ValueSource(strings = {"50,16,244", "50,16,245.00"})
    void testGetTotalTradedValueAsLong(String values) {
        double totalTradedValueAsLong = StockUtils.getTotalTradedValueAsLong(values);
        assertThat(totalTradedValueAsLong).isInstanceOf(Double.class).isIn(5016244d, 5016245.00d);
    }

}