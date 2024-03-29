package com.financialog.controller;

import com.financialog.dto.CommonResponse;
import com.financialog.dto.TradeDetailsDto;
import com.financialog.dto.TradeRequestDto;
import com.financialog.service.TradeService;
import com.financialog.util.FinLogControllerPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FinLogControllerPrefix
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/trades")
    public String getTrades() {
        return "Success";
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/trade/save")
    public CommonResponse<String> saveTrade(@RequestBody TradeRequestDto tradeDto) {
        try {
            return tradeService.addTrade(tradeDto);
        } catch (Exception e) {
            return null;
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/trade/view-all")
    public CommonResponse<List<TradeDetailsDto>> viewAllTrades() {
        return tradeService.viewAllTrades();
    }


}
