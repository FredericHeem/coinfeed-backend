package com.coinfeed.marketfeed.fetcher;

import com.coinfeed.marketfeed.model.TickerModel;

public interface FeedPollerListener {
	void onTicker(TickerModel tickerModel, boolean hasChanged);
	void onError(String error);
}
