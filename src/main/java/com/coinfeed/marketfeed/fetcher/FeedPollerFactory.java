package com.coinfeed.marketfeed.fetcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coinfeed.marketfeed.fetcher.bitstamp.BitstampFeedFetcher;
import com.coinfeed.marketfeed.fetcher.mtgox.MtGoxFeedFetcher;

public class FeedPollerFactory {
	private static final Logger log = LoggerFactory.getLogger(FeedPollerFactory.class);
	
	public static FeedPoller createFeedPoller(
			String driver,
			FeedPollerListener feedPollerListener,
			FeedPollerConfig config){
		log.debug("createFeedPoller: " + driver);
		FeedFetcher feed = null;
		if(driver.compareTo(BitstampFeedFetcher.DRIVER_NAME) == 0){
			feed = new BitstampFeedFetcher(config);
		} else if(driver.compareTo(MtGoxFeedFetcher.DRIVER_NAME) == 0){
			feed = new MtGoxFeedFetcher(config);
		} else {
			log.error(driver + " is not a known feed fetcher ");
			return null;
		}
		FeedPoller feedFetcher = new FeedPoller(feed, feedPollerListener);
		feed.setFeedListener(feedFetcher);
		return feedFetcher;
	}
}
