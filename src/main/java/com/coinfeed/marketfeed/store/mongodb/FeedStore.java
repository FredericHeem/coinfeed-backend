package com.coinfeed.marketfeed.store.mongodb;

import java.net.UnknownHostException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coinfeed.marketfeed.model.TickerModel;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class FeedStore {
	private static final Logger log = LoggerFactory.getLogger(FeedStore.class);
	private MongoClient mongoClient;
	private FeedStoreConfig config = new FeedStoreConfig();
	private DB db;
	private boolean authenticated;
	
	public FeedStore(){
	}
	
	public FeedStore(FeedStoreConfig config){
		super();
		this.config = config;
	}
	
	public List<String> getDatabaseNames() throws UnknownHostException{
		List<String> dbNameList = getClient().getDatabaseNames();
		return dbNameList;
	}
	
	public boolean authenticate() throws UnknownHostException{
		log.debug("Authenticating ...");
		boolean auth = getDb().authenticate(config.getUsername(), config.getPassword().toCharArray());	
		if(auth){
			log.debug("Authentition OK");
		} else {
			log.warn("Authentition KO");
		}
		setAuthenticated(auth);
		return auth;
	}
	
	public void close(){
		try {
			log.debug("close");
			getClient().close();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public void write(TickerModel tickerModel) {
		try {
			log.debug("write " + tickerModel.toString());
			String marketName = tickerModel.getMarketName();
			if(marketName.isEmpty()){
				throw new IllegalArgumentException("empty market name");
			}
			
			DBCollection collection = db.getCollection(marketName);
			log.debug("write #collection " + collection.count());
			collection.insert(createBasicDBObject(tickerModel));
		} catch (Exception e) {
			log.error("write error: " + e.getMessage());
		}
	}

	private BasicDBObject createBasicDBObject(TickerModel tickerModel){
		BasicDBObject basicDBObject = new BasicDBObject("date", tickerModel.getDate()).
		    append("bid", tickerModel.getBid()).
            append("ask", tickerModel.getAsk());
		return basicDBObject;
	}
	
	private MongoClient getClient() throws UnknownHostException{
		if(this.mongoClient == null){
			this.mongoClient = new MongoClient(config.getHostname(), config.getPort());
		}
		return this.mongoClient;
	}
	
	private DB getDb() throws UnknownHostException{
		if(this.db == null){
			this.db = getClient().getDB(config.getDbName());
		}
		return this.db;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}
	
	public FeedStoreConfig getConfig() {
		return config;
	}

	public void setConfig(FeedStoreConfig config) {
		this.config = config;
	}
}
