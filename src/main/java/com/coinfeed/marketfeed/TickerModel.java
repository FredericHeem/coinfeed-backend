package com.coinfeed.marketfeed;

import java.util.Date;

public class TickerModel {
	private String marketName;
	private String bid;
	private String ask;
	private Date date;
	
	public static TickerModel create(
			String marketName, 
			String bid, 
			String ask){
		TickerModel tickerModel = new TickerModel();
		tickerModel.setMarketName(marketName);
		tickerModel.setBid(bid);
		tickerModel.setAsk(ask);
		tickerModel.setDate(new Date());
		return tickerModel;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append(getMarketName()).append(": ");
		builder.append("bid: ").append(getBid()).append(", ");
		builder.append("ask: ").append(getAsk());
		return builder.toString();
	}
	
	public String getAsk() {
		return ask;
	}

	public void setAsk(String ask) {
		this.ask = ask;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public void setMarketName(String market) {
		this.marketName = market;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setDate(Date date) {
		this.date = (Date)date.clone();
	}

	public Date getDate() {
		return (Date)date.clone();
	}	
}
