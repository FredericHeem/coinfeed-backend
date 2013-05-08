package com.coinfeed.marketfeed;

import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class FeedStore {
	private static final Logger log = LoggerFactory.getLogger(FeedStore.class);
	private MongoClient mongoClient;
	private FeedStoreConfig config;
	
	public FeedStore(FeedStoreConfig config){
		this.config = config;
	}
	
	public boolean authenticate() throws UnknownHostException{
		log.debug("Authenticating ...");
		this.mongoClient = new MongoClient(config.getHostname(), config.getPort());
		DB db = mongoClient.getDB(config.getDbName());
		boolean auth = db.authenticate(config.getUsername(), config.getPassword().toCharArray());	
		if(auth){
			log.debug("Authentition OK");
		} else {
			log.warn("Authentition KO");
		}
		return auth;
	}
	
	public void write(TickerModel tickerModel) {
		log.debug("write " + tickerModel.toString());
		
	}

	
}
