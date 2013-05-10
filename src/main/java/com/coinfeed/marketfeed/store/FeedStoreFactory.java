package com.coinfeed.marketfeed.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coinfeed.marketfeed.fetcher.FeedPollerFactory;
import com.coinfeed.marketfeed.store.mongodb.FeedStore;
import com.coinfeed.marketfeed.store.mongodb.FeedStoreConfig;

public class FeedStoreFactory {
	private static final Logger log = LoggerFactory.getLogger(FeedPollerFactory.class);
	private static final String MONGODB = "mongodb";
	public static FeedStore createStore(
			String driver,
			FeedStoreConfig config){
		log.debug("createStore: " + driver);
		FeedStore store = null;
		if(driver.compareTo(MONGODB) == 0){
			store = new FeedStore(config);
		} else {
			log.error(driver + " is not a known feed store ");
			return null;
		}
		return store;
	}
}
