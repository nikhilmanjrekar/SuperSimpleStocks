package com.example.supersimplestocks.util;

import java.math.BigDecimal;

import com.example.supersimplestocks.main.SuperSimpleStocksConstants;

/**
 * @author Nikhil Manjrekar
 * 
 *         This class logs the calculated stock details. Currently it only
 *         prints these details on console. However, it can be easily modified
 *         if we decide to log details in a file.
 *
 */
public class ReportUtil {

	/**
	 * @param dividendYield
	 * @param priceToEarningRatio
	 * @param volumeWeightedStockPrice
	 * @param gbceAllShareIndex
	 * 
	 *            This method logs stocks calculated details on console.
	 */
	public void displayCalculatedResult(BigDecimal dividendYield, BigDecimal priceToEarningRatio,
			BigDecimal volumeWeightedStockPrice, BigDecimal gbceAllShareIndex) {
		System.out.println("|----------------------------------------------------------------------|");
		System.out.println("|                           GBCE STOCKS STATS                          |");
		System.out.println("|----------------------------------------------------------------------|");

		System.out.println(String.format(SuperSimpleStocksConstants.REPORTS_HEADER_FORMAT,
				SuperSimpleStocksConstants.REPORTS_HEADER_DIVIDEND_YIELD,
				SuperSimpleStocksConstants.REPORTS_HEADER_PE_RATIO,
				SuperSimpleStocksConstants.REPORTS_HEADER_VOL_WEIGHTED_STOCK_PRICE,
				SuperSimpleStocksConstants.REPORTS_HEADER_GBCE_ALL_SHARE_INDEX));
		System.out.println("|----------------------------------------------------------------------|");
		System.out.println(String.format(SuperSimpleStocksConstants.REPORTS_DATA_FORMAT, dividendYield, priceToEarningRatio,
				volumeWeightedStockPrice, gbceAllShareIndex));
		System.out.println("|----------------------------------------------------------------------|");

	}

}
