package com.coinfeed.marketfeed.fetcher;


import java.util.concurrent.CountDownLatch;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coinfeed.marketfeed.fetcher.FeedFetcherListener;
import com.coinfeed.marketfeed.fetcher.FeedPollerConfig;
import com.coinfeed.marketfeed.fetcher.bitstamp.BitstampFeedFetcher;
import com.coinfeed.marketfeed.model.TickerModel;

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
		FeedPollerConfig config = new FeedPollerConfig();
		BitstampFeedFetcher bitstampFeed = new BitstampFeedFetcher(config);
		bitstampFeed.setFeedListener(new FeedFetcherListener() {
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
