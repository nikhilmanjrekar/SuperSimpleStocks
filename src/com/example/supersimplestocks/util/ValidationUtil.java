package com.example.supersimplestocks.util;

import java.math.BigDecimal;

import com.example.supersimplestocks.main.SuperSimpleStocksConstants;

/**
 * @author Nikhil Manjrekar
 * 
 *         This class provides implementation to all the validation related
 *         methods.
 *
 */
public class ValidationUtil {

	/**
	 * @param stockSymbol
	 * @param stockCalculationUtil
	 * @return boolean
	 * 
	 *         This method validates the stock symbol. It checks if it matches
	 *         one of the values from (TEA, POP, ALE, GIN, JOE).
	 */
	public boolean validateEnteredStockSymbol(String stockSymbol, StockCalculationUtil stockCalculationUtil) {
		if (stockCalculationUtil.gbceSampleStocksData.containsKey(stockSymbol)) {
			return true;
		} else {
			System.out.println(SuperSimpleStocksConstants.INVALID_STOCK_SYMBOL_MSG);
			System.out.println("\n");
			return false;
		}
	}

	/**
	 * @param marketPrice
	 * @return boolean
	 * 
	 *         This method validates the entered market price. It checks if it
	 *         is a valid number.
	 */
	public boolean validateEnteredMarketPrice(String marketPrice) {
		try {
			new BigDecimal(marketPrice);
			return true;
		} catch (NumberFormatException nfe) {
			System.out.println(SuperSimpleStocksConstants.INVALID_MARKET_PRICE_MSG);
			System.out.println("\n");
		}
		return false;
	}

	/**
	 * @param stockQuantity
	 * @return boolean
	 * 
	 *         This method validates the entered stock quantity to check if it
	 *         is valid number.
	 */
	public boolean validateStockQuantity(String stockQuantity) {
		if (!stockQuantity.matches(SuperSimpleStocksConstants.STOCK_QUANTITY_REGEX)) {
			System.out.println(SuperSimpleStocksConstants.INVALID_STOCK_QUANTITY_MSG);
			System.out.println("\n");
			return false;
		}
		return true;
	}

	/**
	 * @param indicator
	 * @return boolean
	 * 
	 *         This method validates the entered stock indicator to check if it
	 *         matches either BUY or SELL>
	 */
	public boolean validateIndicator(String indicator) {
		if (indicator.equals(SuperSimpleStocksConstants.BUY) || indicator.equals(SuperSimpleStocksConstants.SELL))
			return true;
		else
			System.out.println(SuperSimpleStocksConstants.INVALID_INDICATOR_MSG);
			System.out.println("\n");
			return false;
	}

}
