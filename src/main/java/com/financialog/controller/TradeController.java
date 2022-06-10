package com.financialog.controller;

import org.springframework.web.bind.annotation.RestController;

import com.financialog.dto.TradeDto;

@RestController
public class TradeController {
	
	public TradeDto getAllTrades() {
		return new TradeDto();
	}

}
