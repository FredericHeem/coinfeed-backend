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

public class FeedBaseTest implements FeedListener {
	private static final Logger log = LoggerFactory.getLogger(FeedBaseTest.class);
	private CountDownLatch countDownLatch;
	private TickerModel _tickerModel;
	private FeedFetcherConfig config;
	@Before
	public void setUp() throws Exception {
		countDownLatch = new CountDownLatch(1);
		config = new FeedFetcherConfig();
	}

	@After
	public void tearDown() throws Exception {
		log.debug("tearDown");
		Assert.assertNotNull(_tickerModel);
		Assert.assertNotNull(_tickerModel.getBid());
		Assert.assertNotNull(_tickerModel.getAsk());
		Assert.assertNotNull(_tickerModel.getMarketName());
		log.debug(_tickerModel.toString());
	}

	public void testFetch(FeedBase feed)
	{
		try {
			feed.setFeedListener(this);
			feed.fetch();
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
		testFetch(new BitstampFeed(config));
	}

	@Test
	public void testFetchMtGox()
	{
		testFetch(new MtGoxFeed(config));
	}

	@Override
	public void onFeedFetch(TickerModel tickerModel) {
		log.debug("onFeedFetch " + tickerModel.toString());
		this._tickerModel = tickerModel;
		countDownLatch.countDown(); 
	}

	@Override
	public void onError(String error) {
		log.error("onError " + error);
		countDownLatch.countDown(); 
		Assert.assertTrue(false);
	}

}
