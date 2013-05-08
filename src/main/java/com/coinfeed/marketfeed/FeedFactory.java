package com.coinfeed.marketfeed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeedFactory {
	private static final Logger log = LoggerFactory.getLogger(FeedFactory.class);
	public static final String BITSTAMP_FEED = "Bitstamp";
	public static final String MTGOX_FEED = "MtGox";
	
	public static FeedBase createFeed(String type, FeedListener feedListener){
		FeedBase feed = null;
		if(type.compareTo(BITSTAMP_FEED) == 0){
			feed = new BitstampFeed(feedListener);
		} else if(type.compareTo(MTGOX_FEED) == 0){
			feed = new MtGoxFeed(feedListener);
		} else {
			log.error(type + " is not a known feed provider ");
		}
		return feed;
	}
}
