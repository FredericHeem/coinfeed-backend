package com.coinfeed.marketfeed.fetcher.bitfinex;

import com.coinfeed.marketfeed.fetcher.FeedFetcher;
import com.coinfeed.marketfeed.fetcher.FeedPollerConfig;

public class BitfinexFeedFetcher extends FeedFetcher {
	private static final String QUERY_BITFINEX = "https://bitfinex.com/api/v1/ticker/btcusd";
	public static final String DRIVER_NAME = "Bitfinex";
	public BitfinexFeedFetcher(FeedPollerConfig config) {
		super(config, QUERY_BITFINEX);
		setDecoder(new BitfinexFeedDecoder());
	}
}
