package com.coinfeed.marketfeed.fetcher;

import com.coinfeed.marketfeed.model.TickerModel;

public interface FeedFetcherListener {
	void onFeedFetch(TickerModel tickerModel);
	void onError(String error);
}
