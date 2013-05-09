package com.coinfeed.marketfeed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeedFactory {
	private static final Logger log = LoggerFactory.getLogger(FeedFactory.class);
	
	public static FeedFetcher createFeedPoller(
			String driver,
			FeedListener feedListener,
			FeedFetcherConfig config){
		log.debug("createFeedPoller: " + driver);
		FeedBase feed = null;
		if(driver.compareTo(BitstampFeed.DRIVER_NAME) == 0){
			feed = new BitstampFeed(config);
		} else if(driver.compareTo(MtGoxFeed.DRIVER_NAME) == 0){
			feed = new MtGoxFeed(config);
		} else {
			log.error(driver + " is not a known feed fetcher ");
			return null;
		}
		FeedFetcher feedFetcher = new FeedFetcher(feed, feedListener);
		feed.setFeedListener(feedFetcher);
		return feedFetcher;
	}
}
