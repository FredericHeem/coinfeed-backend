package com.coinfeed.marketfeed.fetcher.btce;

import com.coinfeed.marketfeed.fetcher.FeedFetcher;
import com.coinfeed.marketfeed.fetcher.FeedPollerConfig;

public class BtceFeedFetcher extends FeedFetcher {
	private static final String QUERY = "https://btc-e.com/api/2/btc_usd/ticker";
	public static final String DRIVER_NAME = "Btce";
	
	public BtceFeedFetcher(FeedPollerConfig config) {
		super(config, QUERY);
		setDecoder(new BtceFeedDecoder());
	}
}
