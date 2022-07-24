package com.financialog.controller;

import com.financialog.dto.CommonResponse;
import com.financialog.dto.TradeDto;
import com.financialog.util.FinLogControllerPrefix;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@FinLogControllerPrefix
public class TradeController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/trades")
    public String getTrades() {
        return "Success";
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public CommonResponse<String> saveTrade(@RequestBody TradeDto tradeDto) {
        return new CommonResponse<>(CommonResponse.ResponseCodeEnum.SUCCESS.getCode(),
                "Successfully Entered Trade", "Success");
    }

}
