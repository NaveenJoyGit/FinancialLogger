package com.financialog.controller;

import org.springframework.web.bind.annotation.RestController;

import com.financialog.dto.Trade;

@RestController
public class TradeController {
	
	public Trade getAllTrades() {
		return new Trade();
	}

}
