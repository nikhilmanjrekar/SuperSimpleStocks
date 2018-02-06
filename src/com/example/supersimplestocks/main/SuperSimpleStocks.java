package com.example.supersimplestocks.main;

import java.math.BigDecimal;
import java.util.Scanner;

import com.example.supersimplestocks.service.StocksService;
import com.example.supersimplestocks.service.StocksServiceImpl;

/**
 * @author Nikhil Manjrekar
 *
 *         This is a main class that starts processing stocks.
 */
public class SuperSimpleStocks {

	public static void main(String[] args) {

		// variables to hold validation status
		boolean validStock;
		boolean validMarketPrice;
		boolean validStockQuantity;
		boolean validIndicator;

		System.out.println("|--------------------------------------------------------------------------|");
		System.out.println("|                         Super Simple Stocks                              |");
		System.out.println("|--------------------------------------------------------------------------|");

		// coding against interface rather than implementation
		StocksService stocksService = new StocksServiceImpl();

		try (Scanner scanner = new Scanner(System.in, "UTF-8");) {
			String stockSymbol;
			String marketPrice;
			String continueFlag;
			String stockQuantity;
			String indicator;

			// Prepare GBCE sample stocks data
			stocksService.prepareGbceSampleData();

			do {
				do {
					// Get stock symbol as input
					System.out.println("\n");
					System.out.println("Please enter Stock Symbol (TEA, POP, ALE, GIN, JOE) : ");
					stockSymbol = scanner.nextLine();
					// Validate input stock symbol
					validStock = stocksService.validateEnteredStockSymbol(stockSymbol);
				} while (!validStock);

				do {
					// Get market price as input
					System.out.println("Please enter Market Price : ");
					marketPrice = scanner.nextLine();
					// Validate input market price
					validMarketPrice = stocksService.validateEnteredMarketPrice(marketPrice);
				} while (!validMarketPrice);

				do {
					// Get stock quantity as input
					System.out.println("Please enter Stock Quantity : ");
					stockQuantity = scanner.nextLine();
					// Validate input stock quantity
					validStockQuantity = stocksService.validateStockQuantity(stockQuantity);
				} while (!validStockQuantity);

				do {
					// Get indicator as input
					System.out.println("Please enter Indicator (BUY/SELL) : ");
					indicator = scanner.nextLine();
					// Validate input stock symbol
					validIndicator = stocksService.validateIndicator(indicator);
				} while (!validIndicator);

				// Start processing stock
				BigDecimal marketPriceVal = new BigDecimal(marketPrice);
				int stockQuantityVal = Integer.parseInt(stockQuantity);
				stocksService.processStock(stockSymbol, marketPriceVal, stockQuantityVal, indicator);

				System.out.println("\n");
				System.out.println("Do you want to continue entering stock details : y/n?");
				continueFlag = scanner.nextLine();
			} while (SuperSimpleStocksConstants.YES.equalsIgnoreCase(continueFlag));

			System.out.println("Thank you for using Super Simple Stocks. Have a good day!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
