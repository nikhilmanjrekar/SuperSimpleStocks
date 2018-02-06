# SuperSimpleStocks

This is a stocks processing java application.
Application starts with fetching some user inputs:
1) Stock Symbol
2) Stock market price
3) Quantity
4) Indicator

It records trades to a list and uses them to calculate various factors. Please consider below assumptions:

1) Coding is done in core java.
2) Currently all the input validations are done to ensure data correctness.
3) There is no coding done around BUY or SELL indicators to check the stock quantity.
4) All the data is in memory so trades are recorded till the time program is running.
5) I have considered all the factors as BigDecimal to hold higher and accurate prcesions.
6) SuperSimpleStocks.java has a main method to start execution.
