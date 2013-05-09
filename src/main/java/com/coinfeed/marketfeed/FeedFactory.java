package com.coinfeed.marketfeed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeedFactory {
	private static final Logger log = LoggerFactory.getLogger(FeedFactory.class);
	public static final String BITSTAMP_FEED = "Bitstamp";
	public static final String MTGOX_FEED = "MtGox";
	
	public static FeedFetcher createFeedPoller(String type, FeedListener feedListener, FeedFetcherConfig config){
		FeedBase feed = null;
		if(type.compareTo(BITSTAMP_FEED) == 0){
			feed = new BitstampFeed(config);
		} else if(type.compareTo(MTGOX_FEED) == 0){
			feed = new BitstampFeed(config);
		} else {
			log.error(type + " is not a known feed fetcher ");
			return null;
		}
		FeedFetcher feedFetcher = new FeedFetcher(feed, feedListener);
		feed.setFeedListener(feedFetcher);
		return feedFetcher;
	}
}
