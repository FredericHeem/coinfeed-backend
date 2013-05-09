package com.coinfeed.marketfeed.fetcher.bitstamp;

import com.coinfeed.marketfeed.fetcher.FeedFetcher;
import com.coinfeed.marketfeed.fetcher.FeedPollerConfig;

public class BitstampFeedFetcher extends FeedFetcher {
	//private static final Logger log = LoggerFactory.getLogger(BitstampFeed.class);
	private static final String QUERY_BITSTAMP = "https://www.bitstamp.net/api/ticker/";
	public static final String DRIVER_NAME = "Bitstamp";
	public BitstampFeedFetcher(FeedPollerConfig config) {
		super(config);
		setQuery(QUERY_BITSTAMP);
		setDecoder(new BitstampFeedDecoder());
	}
}
