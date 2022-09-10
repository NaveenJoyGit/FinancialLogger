package com.financialog.dto;

import java.sql.Date;

public class TradeDto {
	
	Integer id;
	StockDto stock;
	TradeDetailsDto details;
	String status;
	Date creationDate;

}
