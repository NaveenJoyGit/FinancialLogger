package com.financialog.controller;

import com.financialog.dto.CommonResponse;
import com.financialog.dto.TradeRequestDto;
import com.financialog.service.TradeService;
import com.financialog.util.FinLogControllerPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@FinLogControllerPrefix
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/trades")
    public String getTrades() {
        return "Success";
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/save")
    public CommonResponse<String> saveTrade(@RequestBody TradeRequestDto tradeDto) {
        return tradeService.addTrade(tradeDto);
    }


}
