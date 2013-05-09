package com.coinfeed.marketfeed;

public class FeedFetcherConfig {
	private String name = "";
	private int pollingPeriod = 10000;

	public void setPollingPeriod(int pollingPeriod) {
		this.pollingPeriod = pollingPeriod;
	}

	public int getPollingPeriod() {
		return pollingPeriod;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
