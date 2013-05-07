package com.coinfeed.marketfeed;

public interface FeedListener {
	public void onFeedFetch(TickerModel tickerModel);
	public void onError(String error);
}
