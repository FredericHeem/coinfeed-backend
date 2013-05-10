package com.coinfeed.marketfeed.fetcher.mtgox;

import com.coinfeed.marketfeed.fetcher.FeedFetcher;
import com.coinfeed.marketfeed.fetcher.FeedPollerConfig;

public class MtGoxFeedFetcher extends FeedFetcher {
	private static final String QUERY_MTGOX = "https://data.mtgox.com/api/2/BTCUSD/money/ticker";
	public static final String DRIVER_NAME = "MtGox";
	
	public MtGoxFeedFetcher(FeedPollerConfig config) {
		super(config, QUERY_MTGOX);
		setDecoder(new MtGoxFeedDecoder());
	}
}
