package com.coinfeed.marketfeed;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeedManager implements FeedListener {
	private static final Logger log = LoggerFactory.getLogger(FeedManager.class);
	private LinkedList<FeedFetcher> feedFetcherList = new LinkedList<FeedFetcher>();
	private FeedStore feedStore;
	private FeedStoreConfig feedStoreConfig;
	
	public FeedManager(){
		setupFeedProviderList();
		this.feedStoreConfig = new FeedStoreConfig();
		this.feedStore = new FeedStore(this.feedStoreConfig);
	}

	public boolean initialize(){
		boolean authenticated = false;
		try {
			authenticated = this.feedStore.authenticate();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return authenticated;
	}
	
	public void startFetch(){
		log.debug("startFetch");
		for(FeedFetcher feedFetcher : feedFetcherList){
			feedFetcher.start();	
		}
	}
	
	@Override
	public void onFeedFetch(TickerModel tickerModel) {
		log.debug("onFeedFetch: " + tickerModel.toString());
		this.feedStore.write(tickerModel);
	}

	@Override
	public void onError(String error) {
		log.error("onError: " + error);
	}
	
	private void setupFeedProviderList(){
		this.feedFetcherList.add(new FeedFetcher(FeedFactory.BITSTAMP_FEED, this));
		this.feedFetcherList.add(new FeedFetcher(FeedFactory.MTGOX_FEED, this));
	}
}
