package com.coinfeed.marketfeed.app;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
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
		app.run(app.createConfig(args), -1);
	}

	/**
	 * @param config
	 * @param runDuration execute for the given seconds
	 */
	public void run(Config config, int runDuration) {
		log.info("run");
		if(config == null){
			log.error("no valid config");
			return;
		}
		
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		
		try {
			FeedManager feedManager = new FeedManager();
			feedManager.configure(config);
			
			if(feedManager.initialize() == false){
				log.error("feedManager.initialize ko");
				return;
			}
			
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
			} catch (FileNotFoundException e) {
				log.error("createConfig: " + e.getMessage());
			} catch (UnsupportedEncodingException e) {
				log.error("createConfig: " + e.getMessage());
			}
		}
		
		return config;
	}
}
