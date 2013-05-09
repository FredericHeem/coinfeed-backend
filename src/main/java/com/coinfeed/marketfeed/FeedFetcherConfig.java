package com.coinfeed.marketfeed;

public class FeedFetcherConfig {
	private String driver = "";
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

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDriver() {
		return driver;
	}
}
