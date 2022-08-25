package com.financialog.util;

import com.financialog.enums.StockConstants;

public class StockUtils {

    /*
    Factor to get approximate value of the stocks actual market capitalization
     */
    public static final double SECTOR_CAP_FACTOR = 600;
    public static final double LARGE_CAP_LOWER_BOUND = 20_000;
    public static final double MID_CAP__LOWER_BOUND = 5_000;

    public static String getSectorByMarketCap(double tradedCap) {
        double approxActualMarketCap = tradedCap * SECTOR_CAP_FACTOR;
        if(approxActualMarketCap > LARGE_CAP_LOWER_BOUND)
            return StockConstants.LARGE_CAP.name();
        else if (approxActualMarketCap < LARGE_CAP_LOWER_BOUND && approxActualMarketCap > MID_CAP__LOWER_BOUND)
            return StockConstants.MID_CAP.name();
        else return StockConstants.SMALL_CAP.name();

    }

    public static double getTotalTradedValueAsLong(String totalTradedValue) {
        totalTradedValue = totalTradedValue.replaceAll(",", "");
        double totalTradedValueAsDouble = Double.parseDouble(totalTradedValue);
        return totalTradedValueAsDouble;
    }
}
