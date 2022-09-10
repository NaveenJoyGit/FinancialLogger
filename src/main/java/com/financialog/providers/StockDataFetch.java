package com.financialog.providers;

import com.financialog.dto.StockData;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public interface StockDataFetch {
    StockData fetchStockData(ApiParameter... params);
}
