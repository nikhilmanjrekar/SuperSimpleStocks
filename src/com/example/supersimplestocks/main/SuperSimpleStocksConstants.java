package com.example.supersimplestocks.main;

public class SuperSimpleStocksConstants {

	public static String YES = "y";
	// Reports
	public static String REPORTS_HEADER_FORMAT = "|%-6s|%-8s|%-8s|%-6s|";
	public static String REPORTS_HEADER_DIVIDEND_YIELD = "DIVIDEND YIELD";
	public static String REPORTS_HEADER_PE_RATIO = "P/E RATIO";
	public static String REPORTS_HEADER_VOL_WEIGHTED_STOCK_PRICE = "VOL WEIGHTED STOCK PRICE";
	public static String REPORTS_HEADER_GBCE_ALL_SHARE_INDEX = "GBCE ALL SHARE INDEX";
	public static String REPORTS_DATA_FORMAT = "|%-14.4f|%-9.4f|%-24.4f|%-20.4f|";
	
	// Stock symbols
	public static String STOCK_SYMBOL_TEA = "TEA";
	public static String STOCK_SYMBOL_POP = "POP";
	public static String STOCK_SYMBOL_ALE = "ALE";
	public static String STOCK_SYMBOL_GIN = "GIN";
	public static String STOCK_SYMBOL_JOE = "JOE";
	
	// Indicators
	public static String BUY = "BUY";
	public static String SELL = "SELL";
	
	//Validations
	public static String STOCK_QUANTITY_REGEX = "[0-9]+";
	public static String INVALID_STOCK_SYMBOL_MSG = "Invalid Stock Symbol. Please note valid Stock Symbols --> TEA, POP, ALE, GIN, JOE";
	public static String INVALID_MARKET_PRICE_MSG = "Invalid Market Price. Please enter valid numeric Market Price.";
	public static String INVALID_STOCK_QUANTITY_MSG = "Invalid Stock Quantity. Please enter valid numeric Stock Quantity.";
	public static String INVALID_INDICATOR_MSG = "Invalid Indicator. Please note valid indicator --> BUY, SELL";
}
