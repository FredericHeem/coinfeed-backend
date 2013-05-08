package com.coinfeed.marketfeed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BitstampFeed extends FeedBase implements IFeed {
	private static final Logger log = LoggerFactory.getLogger(BitstampFeed.class);
	private static final String QUERY_BITSTAMP = "https://www.bitstamp.net/api/ticker/";

	public BitstampFeed(FeedListener feedListener) {
		super(feedListener);
		setQuery(QUERY_BITSTAMP);
	}

	protected void onFeedReceived(String message) {
		log.debug("onFeedReceived:" + message);
		TickerModel tickerModel = BitstampFeedDecoder.decode(message);
		getFeedListener().onFeedFetch(tickerModel);
	}
}
