package com.coinfeed.marketfeed;

public class TickerModel {
	private String marketName;
	private String bid;
	private String ask;
	
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
}
