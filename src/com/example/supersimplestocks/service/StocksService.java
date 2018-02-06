package com.example.supersimplestocks.service;

import java.math.BigDecimal;

/**
 * @author Nikhil Manjrekar 
 * 		   This is an interface that defines methods for stock
 *         processing
 */
public interface StocksService {

	public boolean validateEnteredStockSymbol(String stockSymbol);

	public boolean validateEnteredMarketPrice(String marketPrice);

	public void processStock(String stockSymbol, BigDecimal marketPriceVal, int stockQuantityVal, String indicator);

	public void prepareGbceSampleData();

	public boolean validateStockQuantity(String stockQuantity);

	public boolean validateIndicator(String indicator);

}
