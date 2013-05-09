package com.coinfeed.marketfeed;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeedManager implements FeedListener {
	private static final Logger log = LoggerFactory.getLogger(FeedManager.class);
	private LinkedList<FeedFetcher> feedFetcherList = new LinkedList<FeedFetcher>();
	private FeedStore feedStore;
	private Config config = new Config();
	
	public FeedManager(){
		setupFeedProviderList();
		this.feedStore = new FeedStore();
	}

	public void configure(Config config){
		if(config != null){
			this.config = config;
			if(config.stores != null && config.stores.size() > 0){
				feedStore.setConfig(config.stores.get(0));
			}
			else
			{
				log.error("configure cannot found store config");
			}
		}
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

	public void setConfig(Config config) {
		this.config = config;
	}

	public Config getConfig() {
		return config;
	}
}
