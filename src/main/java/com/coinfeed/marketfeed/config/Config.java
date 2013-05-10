package com.coinfeed.marketfeed.config;

import java.util.LinkedList;
import java.util.List;

import com.coinfeed.marketfeed.fetcher.FeedPollerConfig;
import com.coinfeed.marketfeed.store.FeedStoreConfig;

public class Config {
	public String name;
	public List<FeedPollerConfig> fetchers = new LinkedList<FeedPollerConfig>();
	public List<FeedStoreConfig> stores = new LinkedList<FeedStoreConfig>();
	
	public Config(){
	}
}
