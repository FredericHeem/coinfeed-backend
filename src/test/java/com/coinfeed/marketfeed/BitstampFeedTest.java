package com.coinfeed.marketfeed;


import java.util.concurrent.CountDownLatch;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BitstampFeedTest {
	private static final Logger log = LoggerFactory.getLogger(BitstampFeedTest.class);
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFetch()
	{
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		FeedFetcherConfig config = new FeedFetcherConfig();
		BitstampFeed bitstampFeed = new BitstampFeed(config);
		bitstampFeed.setFeedListener(new FeedListener() {
			@Override
			public void onError(String error) {
				log.error("onError " + error);
				Assert.assertTrue(false);
				countDownLatch.countDown(); 
			}

			@Override
			public void onFeedFetch(TickerModel tickerModel) {
				countDownLatch.countDown(); 
			}
		});

		try {
			bitstampFeed.fetch();
			countDownLatch.await();
		}
		catch(Exception exception){

		}
	}
}
