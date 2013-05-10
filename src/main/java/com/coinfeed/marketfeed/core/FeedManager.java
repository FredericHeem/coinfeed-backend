package com.coinfeed.marketfeed.core;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coinfeed.marketfeed.app.FeedManagerException;
import com.coinfeed.marketfeed.config.Config;
import com.coinfeed.marketfeed.fetcher.FeedPoller;
import com.coinfeed.marketfeed.fetcher.FeedPollerConfig;
import com.coinfeed.marketfeed.fetcher.FeedPollerFactory;
import com.coinfeed.marketfeed.fetcher.FeedPollerListener;
import com.coinfeed.marketfeed.model.TickerModel;
import com.coinfeed.marketfeed.store.FeedStoreConfig;
import com.coinfeed.marketfeed.store.FeedStoreFactory;
import com.coinfeed.marketfeed.store.mongodb.FeedStore;

public class FeedManager implements FeedPollerListener {
	private static final Logger log = LoggerFactory.getLogger(FeedManager.class);
	private LinkedList<FeedPoller> feedFetcherPollerList = new LinkedList<FeedPoller>();
	private LinkedList<FeedStore> feedStoreList = new LinkedList<FeedStore>();
	private Config config;

	public FeedManager(Config config){
		this.config = config;
	}

	public void configure() throws FeedManagerException{
		setupFeedStoreList();
		setupFeedPollerList();
	}
	
	public void initialize() throws FeedManagerException{
		storeAuthenticate();
	}
	
	private void storeAuthenticate() throws FeedManagerException {
		log.debug("storeAuthenticate #stores " +  feedStoreList.size());
		for(FeedStore feedStore : feedStoreList){
			log.debug("storeAuthenticate " + feedStore.getConfig().toString());
			boolean authenticated = false;
			try {
				authenticated = feedStore.authenticate();
			} catch (Exception e) {
				logAndthrow(FeedManagerException.DB_AUTHENTICATION, e.getMessage());
			}
			if(authenticated == false){
				logAndthrow(FeedManagerException.DB_AUTHENTICATION, "db authentication failed to " + feedStore.getConfig().toString());
			}
		}
	}
	public void startFetch(){
		log.debug("startFetch #fetchers " + feedFetcherPollerList.size());
		for(FeedPoller feedPoller : feedFetcherPollerList){
			feedPoller.start();	
		}
	}
	
	@Override
	public void onTicker(TickerModel tickerModel, boolean changed) {
		log.debug("onTicker:      " + tickerModel.toString());
		if(changed == true){
			writeTickerToStores(tickerModel);
		}
	}

	@Override
	public void onError(String error) {
		log.error("onError: " + error);
	}
	
	private void writeTickerToStores(TickerModel tickerModel){
		for(FeedStore feedStore : feedStoreList){
			feedStore.write(tickerModel);
		}
	}
		
	private void setupFeedPollerList() throws FeedManagerException{
		if(getConfig().fetchers.size() == 0){
			logAndthrow(FeedManagerException.CONFIGURATION, "no feed fetcher configured");
		}
		
		for(FeedPollerConfig config : getConfig().fetchers){
			log.info("setupFeedPollerList: " + config.getName());
			FeedPoller feedPoller = FeedPollerFactory.createFeedPoller(config.getDriver(), this, config);
			if(feedPoller != null){
				this.feedFetcherPollerList.add(feedPoller);
			} else {
				logAndthrow(FeedManagerException.CONFIGURATION, config.getName() + " is not a known feed fetcher");
			}
		}
	}

	private void setupFeedStoreList() throws FeedManagerException{
		if(getConfig().stores.size() == 0){
			logAndthrow(FeedManagerException.CONFIGURATION, "no feed store configured");
		}
		
		for(FeedStoreConfig config : getConfig().stores){
			log.info("setupFeedStoreList: " + config.toString());
			FeedStore feedStore = FeedStoreFactory.createStore(config.getDriver(), config);
			if(feedStore != null){
				this.feedStoreList.add(feedStore);
			} else {
				logAndthrow(FeedManagerException.CONFIGURATION, config.getDriver() + " is not a known feed store");
			}
		}
	}
	
	private void logAndthrow(String type, String message) throws FeedManagerException{
		log.error("type " + type + ": " + message);
		throw new FeedManagerException(type, message);
		
	}
	public Config getConfig() {
		return config;
	}
}
