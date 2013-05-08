package com.coinfeed.marketfeed;

public class TickerModel {

	private String bid;
	private String ask;
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
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


	
}
