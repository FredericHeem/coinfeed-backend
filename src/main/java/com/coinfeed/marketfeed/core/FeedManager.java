package com.coinfeed.marketfeed.core;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coinfeed.marketfeed.config.Config;
import com.coinfeed.marketfeed.fetcher.FeedPoller;
import com.coinfeed.marketfeed.fetcher.FeedPollerConfig;
import com.coinfeed.marketfeed.fetcher.FeedPollerFactory;
import com.coinfeed.marketfeed.fetcher.FeedPollerListener;
import com.coinfeed.marketfeed.model.TickerModel;
import com.coinfeed.marketfeed.store.mongodb.FeedStore;

public class FeedManager implements FeedPollerListener {
	private static final Logger log = LoggerFactory.getLogger(FeedManager.class);
	private LinkedList<FeedPoller> feedFetcherPollerList = new LinkedList<FeedPoller>();
	private FeedStore feedStore = new FeedStore();
	private Config config = new Config();
	
	public FeedManager(){
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
		
		setupFeedPollerList();
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
		log.debug("startFetch #fetchers " + feedFetcherPollerList.size());
		for(FeedPoller feedPoller : feedFetcherPollerList){
			feedPoller.start();	
		}
	}
	
	@Override
	public void onTicker(TickerModel tickerModel, boolean changed) {
		log.debug("onTicker:      " + tickerModel.toString());
		if(changed == true){
			this.feedStore.write(tickerModel);
		}
	}

	@Override
	public void onError(String error) {
		log.error("onError: " + error);
	}
	
	private void setupFeedPollerList(){
		if(getConfig().fetchers == null){
			log.error("no feed fetcher configured");
			return;
		}
		
		for(FeedPollerConfig config : getConfig().fetchers){
			log.info("setupFeedPollerList: " + config.getName());
			FeedPoller feedPoller = FeedPollerFactory.createFeedPoller(config.getDriver(), this, config);
			if(feedPoller != null){
				this.feedFetcherPollerList.add(feedPoller);
			} else {
				log.error("setupFeedPollerList: " + config.getName() + " is not a known feed fetcher");
			}
		}
	}

	public Config getConfig() {
		return config;
	}
}
