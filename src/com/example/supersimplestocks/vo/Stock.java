package com.example.supersimplestocks.vo;

/**
 * @author Nikhil Manjrekar
 *
 */
public class Stock {

	private String symbol;
	private StockType stockType;
	private int lastDividend;
	private int fixedDividend;
	private int parValue;
		
	public Stock(String symbol, StockType stockType, int lastDividend, int fixedDividend, int parValue) {
		this.symbol = symbol;
		this.stockType = stockType;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public StockType getStockType() {
		return stockType;
	}
	public void setStockType(StockType stockType) {
		this.stockType = stockType;
	}
	public int getLastDividend() {
		return lastDividend;
	}
	public void setLastDividend(int lastDividend) {
		this.lastDividend = lastDividend;
	}
	public int getFixedDividend() {
		return fixedDividend;
	}
	public void setFixedDividend(int fixedDividend) {
		this.fixedDividend = fixedDividend;
	}
	public int getParValue() {
		return parValue;
	}
	public void setParValue(int parValue) {
		this.parValue = parValue;
	}
	
	
	
	
}
