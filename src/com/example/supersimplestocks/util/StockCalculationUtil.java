package com.example.supersimplestocks.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.supersimplestocks.main.SuperSimpleStocksConstants;
import com.example.supersimplestocks.vo.Indicator;
import com.example.supersimplestocks.vo.Stock;
import com.example.supersimplestocks.vo.StockType;
import com.example.supersimplestocks.vo.Trade;

/**
 * @author Nikhil Manjrekar
 * 
 *         This is a util class that provides implementation for stocks
 *         calculations methods.
 *
 */
public class StockCalculationUtil {

	Map<String, Stock> gbceSampleStocksData = new HashMap<>();
	List<Trade> trades = new ArrayList<Trade>();

	/**
	 * This method puts stocks master data into a hashmap.
	 */
	public void prepareGbceSampleData() {
		// Enter data into a hashmap
		gbceSampleStocksData.put(SuperSimpleStocksConstants.STOCK_SYMBOL_TEA,
				new Stock(SuperSimpleStocksConstants.STOCK_SYMBOL_TEA, StockType.COMMOM, 0, 0, 100));
		gbceSampleStocksData.put(SuperSimpleStocksConstants.STOCK_SYMBOL_POP,
				new Stock(SuperSimpleStocksConstants.STOCK_SYMBOL_POP, StockType.COMMOM, 8, 0, 100));
		gbceSampleStocksData.put(SuperSimpleStocksConstants.STOCK_SYMBOL_ALE,
				new Stock(SuperSimpleStocksConstants.STOCK_SYMBOL_ALE, StockType.COMMOM, 23, 0, 60));
		gbceSampleStocksData.put(SuperSimpleStocksConstants.STOCK_SYMBOL_GIN,
				new Stock(SuperSimpleStocksConstants.STOCK_SYMBOL_GIN, StockType.PREFERRED, 8, 2, 100));
		gbceSampleStocksData.put(SuperSimpleStocksConstants.STOCK_SYMBOL_JOE,
				new Stock(SuperSimpleStocksConstants.STOCK_SYMBOL_JOE, StockType.COMMOM, 13, 0, 250));
	}

	/**
	 * @param stockSymbol
	 * @param marketPriceVal
	 * @param stockQuantityVal
	 * @param indicator
	 * 
	 *            This method records a trade to a list.
	 */
	public void recordTrade(String stockSymbol, BigDecimal marketPriceVal, int stockQuantityVal, String indicator) {
		Trade trade = null;

		// Record a trade with BUY or SELL indicator. Currently there is no
		// logic applied around stock quantity based on the indicators.
		if (indicator.equals(SuperSimpleStocksConstants.BUY)) {
			trade = new Trade(stockSymbol, new Date(), stockQuantityVal, Indicator.BUY, marketPriceVal);
		} else {
			trade = new Trade(stockSymbol, new Date(), stockQuantityVal, Indicator.SELL, marketPriceVal);
		}

		// Add trade to a list
		trades.add(trade);
	}

	/**
	 * @param stockSymbol
	 * @param marketPriceVal
	 * @return dividendYield
	 * 
	 *         This method calculates dividend yield. Formula : (Last
	 *         Dividend)/(Market Price) for COMMON. (Fixed Dividend * Par
	 *         Value)/(Market Price) for PREFERRED.
	 */
	public BigDecimal calculateDividendYield(String stockSymbol, BigDecimal marketPriceVal) {
		BigDecimal dividendYield = null;
		Stock stock = gbceSampleStocksData.get(stockSymbol);

		if (null != stock) {
			if ((StockType.COMMOM).equals(stock.getStockType())) {
				// Apply formula for COMMON
				dividendYield = new BigDecimal(stock.getLastDividend()).divide(marketPriceVal, 4, RoundingMode.HALF_UP);
			} else {
				// Apply formula for PREFERRED
				dividendYield = new BigDecimal((stock.getFixedDividend() / 100) * stock.getParValue())
						.divide(marketPriceVal, 2, RoundingMode.HALF_UP);
			}
		}
		return dividendYield;
	}

	/**
	 * @param dividendYield
	 * @param marketPriceVal
	 * @return priceToEarningRatio
	 * 
	 *         This method calculates the P/E Ratio. Formula : (Market
	 *         Price)/Dividend
	 */
	public BigDecimal calculatePriceToEarningRatio(BigDecimal dividendYield, BigDecimal marketPriceVal) {
		BigDecimal priceToEarningRatio = null;
		try {
			priceToEarningRatio = marketPriceVal.divide(dividendYield, 4, RoundingMode.HALF_UP);
		} catch (ArithmeticException ae) {
			return BigDecimal.ZERO;
		}
		return priceToEarningRatio;
	}

	/**
	 * @param stockSymbol
	 * @return volumeWeightedStockPrice
	 * 
	 *         This method calculates the volume weighted stock price. Formula :
	 *         (<Summation>Trade Price * Quantity)/(<Summation>Quantity)
	 */
	public BigDecimal calculateVolumeWeightedStockPrice(String stockSymbol) {
		BigDecimal volumeWeightedStockPrice = BigDecimal.ZERO;
		BigDecimal quantity = BigDecimal.ZERO;
		BigDecimal tradePrice = BigDecimal.ONE;

		// Calculate 15 minutes past dateTime for comparison
		Calendar fifteenMinsPastDate = Calendar.getInstance();
		fifteenMinsPastDate.add(Calendar.MINUTE, -15);

		// Java 8 Stream feature to filter the list to collect only those trades
		// that are made in last 15 minutes.
		List<Trade> filteredTrades = trades.stream()
				.filter(trade -> trade.getTimestamp().getTime() >= fifteenMinsPastDate.getTimeInMillis()
						&& trade.getStockSymbol().equals(stockSymbol))
				.collect(Collectors.toList());

		// Calculate summation of trade price * quantity and summation of
		// quantity
		if (null != filteredTrades && !filteredTrades.isEmpty()) {
			for (Trade trade : filteredTrades) {
				tradePrice = tradePrice.add(trade.getPrice().multiply(new BigDecimal(trade.getQuantity())));
				quantity = quantity.add(new BigDecimal(trade.getQuantity()));
			}

			// to account for the object initialization trade price
			tradePrice = tradePrice.subtract(BigDecimal.ONE);
			// Calculate volume Weighted Stock Price
			volumeWeightedStockPrice = tradePrice.divide(quantity, 4, RoundingMode.HALF_UP);
		}
		return volumeWeightedStockPrice;
	}

	/**
	 * @return BigDecimal
	 * 
	 *         This method calculates the GBCE all share index Formula : nth
	 *         root of all share prices
	 */
	public BigDecimal calculateGbceAllShareIndex() {
		BigDecimal gbceTradePrice = BigDecimal.ONE;

		// Java 8 feature to multiply trade prices of all the recorded trades.
		gbceTradePrice = trades.stream().map(Trade::getPrice).reduce(BigDecimal.ONE, (a, b) -> a.multiply(b));

		// calculate the nth root of gbceTradePrice where n is total number of
		// trades.
		return new BigDecimal(Math.pow(gbceTradePrice.doubleValue(), 1.0 / trades.size())).setScale(4,
				BigDecimal.ROUND_HALF_UP);
	}
}
