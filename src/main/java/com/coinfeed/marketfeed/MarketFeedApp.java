package com.coinfeed.marketfeed;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MarketFeedApp {
	private static final Logger log = LoggerFactory.getLogger(MarketFeedApp.class);
	
	public static void main(String[] args) {
		MarketFeedApp app = new MarketFeedApp();
		app.run(args);

	}

	private void run(String[] args) {
		log.info("run");
		final CountDownLatch countDownLatch = new CountDownLatch(1);

		FeedFetcher feedFetcher = new FeedFetcher(new FeedListener() {
			@Override
			public void onError(String error) {
				log.error(error);
			}

			@Override
			public void onFeedFetch(TickerModel tickerModel) {
				log.info(tickerModel.toString());
			}
		});

		try {
			feedFetcher.start();
			countDownLatch.await();
		} catch (Exception exception) {
			log.error(exception.getMessage());
		}
	}
}
