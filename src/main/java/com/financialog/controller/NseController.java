package com.financialog.controller;

import com.financialog.dto.CommonResponse;
import com.financialog.dto.StockData;
import com.financialog.service.NseDataService;
import com.financialog.util.FinLogControllerPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FinLogControllerPrefix
public class NseController {

    @Autowired
    NseDataService nseDataService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/nse/{symbol}")
    public CommonResponse<StockData> getNseStockData(@PathVariable("symbol") String symbol) {
        return nseDataService.getNseStockData(symbol);
    }

}
