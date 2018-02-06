package com.example.supersimplestocks.service;

import java.math.BigDecimal;

import com.example.supersimplestocks.util.ReportUtil;
import com.example.supersimplestocks.util.StockCalculationUtil;
import com.example.supersimplestocks.util.ValidationUtil;

/**
 * @author Nikhil Manjrekar
 *
 *         This is a service implementation class that provides an
 *         implementation for stocks processing methods.
 */
public class StocksServiceImpl implements StocksService {

	ValidationUtil validationUtil = new ValidationUtil();
	StockCalculationUtil stockCalculationUtil = new StockCalculationUtil();
	ReportUtil reportUtil = new ReportUtil();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.supersimplestocks.service.StocksService#prepareGbceSampleData
	 * ()
	 * 
	 * This method makes a util call to prepare GBCE stocks master data.
	 */
	@Override
	public void prepareGbceSampleData() {
		stockCalculationUtil.prepareGbceSampleData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.supersimplestocks.service.StocksService#
	 * validateEnteredStockSymbol(java.lang.String)
	 * 
	 * This method validates the entered stock symbol. Allowed set is GBCE
	 * sample stock symbols.
	 */
	@Override
	public boolean validateEnteredStockSymbol(String stockSymbol) {
		return validationUtil.validateEnteredStockSymbol(stockSymbol, stockCalculationUtil);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.supersimplestocks.service.StocksService#
	 * validateEnteredMarketPrice(java.lang.String)
	 * 
	 * This method validates the entered market price.
	 */
	@Override
	public boolean validateEnteredMarketPrice(String marketPrice) {
		return validationUtil.validateEnteredMarketPrice(marketPrice);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.supersimplestocks.service.StocksService#validateStockQuantity
	 * (java.lang.String)
	 * 
	 * This method validates the entered stock quantity. At the moment it only
	 * checks for the numeric value.
	 */
	@Override
	public boolean validateStockQuantity(String stockQuantity) {
		return validationUtil.validateStockQuantity(stockQuantity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.supersimplestocks.service.StocksService#validateIndicator(
	 * java.lang.String)
	 * 
	 * This method validates the entered Indicator. Allowed set is 'BUY' or
	 * 'SELL'.
	 */
	@Override
	public boolean validateIndicator(String indicator) {
		return validationUtil.validateIndicator(indicator);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.supersimplestocks.service.StocksService#processStock(java.
	 * lang.String, java.math.BigDecimal, int, java.lang.String)
	 * 
	 * This method processes the entered stock details and calculates dividend
	 * yield, P/E Ratio, volume weighted stock price and GBCE all share index.
	 * It also makes a util call to log the calculated details.
	 */
	@Override
	public void processStock(String stockSymbol, BigDecimal marketPriceVal, int stockQuantityVal, String indicator) {
		// Record a trade to a list
		stockCalculationUtil.recordTrade(stockSymbol, marketPriceVal, stockQuantityVal, indicator);

		// Calculate dividend yield
		BigDecimal dividendYield = stockCalculationUtil.calculateDividendYield(stockSymbol, marketPriceVal);

		// Calculate P/E Ratio (Price to earning ratio)
		BigDecimal priceToEarningRatio = stockCalculationUtil.calculatePriceToEarningRatio(dividendYield,
				marketPriceVal);

		// Calculate volume weighted stock price
		BigDecimal volumeWeightedStockPrice = stockCalculationUtil.calculateVolumeWeightedStockPrice(stockSymbol);

		// Calculate GBCE all share index
		BigDecimal gbceAllShareIndex = stockCalculationUtil.calculateGbceAllShareIndex();

		// Log the calculated details
		reportUtil.displayCalculatedResult(dividendYield, priceToEarningRatio, volumeWeightedStockPrice,
				gbceAllShareIndex);

	}
}
