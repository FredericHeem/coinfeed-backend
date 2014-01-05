package com.coinfeed.marketfeed.fetcher.justcoin;

import com.coinfeed.marketfeed.fetcher.FeedFetcher;
import com.coinfeed.marketfeed.fetcher.FeedPollerConfig;

public class JustcoinFeedFetcher extends FeedFetcher {
	private static final String QUERY_JUSTCOIN = "https://justcoin.com/api/v1/markets";
	public static final String DRIVER_NAME = "Justcoin";
	public JustcoinFeedFetcher(FeedPollerConfig config) {
		super(config, QUERY_JUSTCOIN);
		setDecoder(new JustcoinFeedDecoder());
	}
}
