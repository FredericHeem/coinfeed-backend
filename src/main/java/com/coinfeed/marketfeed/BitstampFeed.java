package com.coinfeed.marketfeed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BitstampFeed extends FeedBase implements IFeed{
	private static final Logger log = LoggerFactory.getLogger(BitstampFeed.class);
	private static final String baseUrl = "https://www.bitstamp.net/api/ticker/";
	
	public BitstampFeed(FeedListener feedListener){
		super(feedListener);
		log.debug("BitstampFeed ctor");
	}
	
	public void fetch(){
		
	}
}
