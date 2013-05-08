package com.coinfeed.marketfeed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeedManager implements FeedListener {
	private static final Logger log = LoggerFactory.getLogger(FeedManager.class);
	private FeedFetcher feedFetcher;
	private FeedStore feedStore;
	private FeedStoreConfig feedStoreConfig;
	
	public FeedManager(){
		this.feedFetcher = new FeedFetcher(this);
		this.feedStoreConfig = new FeedStoreConfig();
		this.feedStore = new FeedStore(this.feedStoreConfig);
	}

	public void startFetch(){
		log.debug("startFetch");
		feedFetcher.start();
	}
	
	@Override
	public void onFeedFetch(TickerModel tickerModel) {
		log.debug("onFeedFetch");
		this.feedStore.write(tickerModel);
	}

	@Override
	public void onError(String error) {
	}
}
