package com.coinfeed.marketfeed.app;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coinfeed.marketfeed.config.Config;
import com.coinfeed.marketfeed.config.ConfigReader;
import com.coinfeed.marketfeed.core.FeedManager;

public class MarketFeedApp {
	private static final Logger log = LoggerFactory.getLogger(MarketFeedApp.class);
	
	public static void main(String[] args) {
		MarketFeedApp app = new MarketFeedApp();
		Config config = app.createConfig(args);
		if(config == null){
			log.error("no config provided");
			return;
		}
		app.run(config, -1);
	}

	/**
	 * @param config
	 * @param runDuration execute for the given seconds
	 */
	public void run(Config config, int runDuration) {
		log.info("run");
		
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		
		try {
			FeedManager feedManager = new FeedManager(config);
			feedManager.configure();
			feedManager.initialize();
			feedManager.startFetch();
			
			if(runDuration <= 0){
				countDownLatch.await();
			} else {
				if(countDownLatch.await(runDuration, TimeUnit.SECONDS)){
					log.warn("run: interrupted");
				}
			}
		} catch (Exception exception) {
			log.error(exception.getMessage());
		}
	}
	
	public Config createConfig(String[] args){
		Config config = null;
		if(args.length > 0){
			String configFile = args[0];
			try {
				config = ConfigReader.createFromFilename(configFile);
			} catch (Exception e) {
				log.error("createConfig: " + e.getMessage());
			}
		}
		
		return config;
	}
}
