package com.coinfeed.marketfeed;

public interface FeedListener {
	void onFeedFetch(TickerModel tickerModel);
	void onError(String error);
}
