package com.coinfeed.marketfeed;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MarketFeedApp {
	private static final Logger log = LoggerFactory.getLogger(MarketFeedApp.class);
	
	public static void main(String[] args) {
		MarketFeedApp app = new MarketFeedApp();
		app.run(createConfig(args));
	}

	private void run(Config config) {
		log.info("run");
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		
		try {
			FeedManager feedManager = new FeedManager();
			feedManager.configure(config);
			
			if(feedManager.initialize() == false){
				log.error("feedManager.initialize ko");
				return;
			}
			
			feedManager.startFetch();
			countDownLatch.await();
		} catch (Exception exception) {
			log.error(exception.getMessage());
		}
	}
	
	private static Config createConfig(String[] args){
		Config config = null;
		if(args.length > 0){
			String configFile = args[0];
			try {
				config = ConfigReader.createFromFilename(configFile);
			} catch (FileNotFoundException e) {
				log.error("createConfig: " + e.getMessage());
			} catch (UnsupportedEncodingException e) {
				log.error("createConfig: " + e.getMessage());
			}
		}
		
		return config;
	}
}
