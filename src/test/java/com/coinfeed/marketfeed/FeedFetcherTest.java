package com.coinfeed.marketfeed;


import static org.junit.Assert.fail;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeedFetcherTest implements FeedListener {
	private static final Logger log = LoggerFactory.getLogger(FeedFetcherTest.class);
	private CountDownLatch countDownLatch;
	private FeedFetcherConfig config = new FeedFetcherConfig();
	@Before
	public void setUp() throws Exception {
		countDownLatch = new CountDownLatch(2);
		config.setPollingPeriod(5000);
	}

	@After
	public void tearDown() throws Exception {
	}

	public void testFetch(FeedFetcher feedFetcher)
	{
		try {
			feedFetcher.start();
			if(countDownLatch.await(60, TimeUnit.SECONDS) == false){
				fail("response was not received");
			}
		}
		catch(Exception exception){
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void testFetchBitstamp()
	{
		testFetch(FeedFactory.createFeedPoller(FeedFactory.BITSTAMP_FEED, this, config));
	}

	@Test
	public void testFetchMtGox()
	{
		testFetch(FeedFactory.createFeedPoller(FeedFactory.MTGOX_FEED, this, config));
	}

	@Override
	public void onFeedFetch(TickerModel tickerModel) {
		log.debug("onFeedFetch " + tickerModel.toString());
		countDownLatch.countDown(); 
	}

	@Override
	public void onError(String error) {
		log.error("onError " + error);
		countDownLatch.countDown(); 
		Assert.assertTrue(false);
	}
}
