package com.coinfeed.marketfeed;

import java.util.LinkedList;
import java.util.List;

public class Config {
	public String name;
	public List<FeedFetcherConfig> fetchers;
	public List<FeedStoreConfig> stores = new LinkedList<FeedStoreConfig>();
	
	public Config(){
	}
}
