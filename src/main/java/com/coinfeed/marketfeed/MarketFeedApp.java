package com.coinfeed.marketfeed;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MarketFeedApp {
	private static final Logger log = LoggerFactory.getLogger(MarketFeedApp.class);
	
	public static void main(String[] args) {
		MarketFeedApp app = new MarketFeedApp();
		app.run();
	}

	private void run() {
		log.info("run");
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		
		try {
			FeedManager feedManager = new FeedManager();
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
}
