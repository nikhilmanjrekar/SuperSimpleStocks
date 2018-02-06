package com.example.supersimplestocks.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Nikhil Manjrekar
 *
 */
public class Trade {

	private String stockSymbol;
	private Date timestamp;
	private int quantity;
	private Indicator indicator;
	private BigDecimal price;
		
	public Trade(String stockSymbol, Date timestamp, int quantity, Indicator indicator, BigDecimal price) {
		this.stockSymbol = stockSymbol;
		this.timestamp = timestamp;
		this.quantity = quantity;
		this.indicator = indicator;
		this.price = price;
	}
	
	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Indicator getIndicator() {
		return indicator;
	}
	public void setIndicator(Indicator indicator) {
		this.indicator = indicator;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
	
	
}
