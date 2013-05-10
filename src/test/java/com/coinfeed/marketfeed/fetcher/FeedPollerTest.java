package com.coinfeed.marketfeed.fetcher;


import static org.junit.Assert.fail;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coinfeed.marketfeed.fetcher.FeedPoller;
import com.coinfeed.marketfeed.fetcher.FeedPollerConfig;
import com.coinfeed.marketfeed.fetcher.FeedPollerFactory;
import com.coinfeed.marketfeed.fetcher.FeedPollerListener;
import com.coinfeed.marketfeed.fetcher.bitstamp.BitstampFeedFetcher;
import com.coinfeed.marketfeed.fetcher.mtgox.MtGoxFeedFetcher;
import com.coinfeed.marketfeed.model.TickerModel;

public class FeedPollerTest implements FeedPollerListener {
	private static final Logger log = LoggerFactory.getLogger(FeedPollerTest.class);
	private CountDownLatch countDownLatch;
	private FeedPollerConfig config = new FeedPollerConfig();
	@Before
	public void setUp() throws Exception {
		countDownLatch = new CountDownLatch(2);
		config.setPollingPeriod(5000);
	}

	@After
	public void tearDown() throws Exception {
	}

	public void testFetch(FeedPoller feedPoller)
	{
		try {
			feedPoller.start();
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
		testFetch(FeedPollerFactory.createFeedPoller(BitstampFeedFetcher.DRIVER_NAME, this, config));
	}

	@Test
	public void testFetchMtGox()
	{
		testFetch(FeedPollerFactory.createFeedPoller(MtGoxFeedFetcher.DRIVER_NAME, this, config));
	}

	public void testFetch404(String url404){
		countDownLatch = new CountDownLatch(1);
		config.setUrl(url404);
		FeedPoller feedPoller = FeedPollerFactory.createFeedPoller(
				BitstampFeedFetcher.DRIVER_NAME, 
				new FeedPollerListener() {
			
			@Override
			public void onTicker(TickerModel tickerModel, boolean hasChanged) {
				fail("KO");
			}
			
			@Override
			public void onError(String error) {
				log.error("onError");
				countDownLatch.countDown();
			}
		}, config);
		try {
			feedPoller.start();
			if(countDownLatch.await(60, TimeUnit.SECONDS) == false){
				fail("response was not received");
			}
		}
		catch(Exception exception){
			fail(exception.getMessage());
		}
	}
	@Test
	public void testFetchBitstamp404()
	{
		testFetch404("https://www.bitstamp.net/api/tickerN");
	}

	@Test
	public void testFetchMtGox404()
	{
		testFetch404("https://data.mtgox.com/api/2/BTCUSD/money/tickerN");
	}

	@Test
	public void testFetchAndStop()
	{
		try {
			FeedPoller feedPoller = FeedPollerFactory.createFeedPoller(BitstampFeedFetcher.DRIVER_NAME, this, config);
			feedPoller.start();
			feedPoller.stop();
		}
		catch(Exception exception){
			fail(exception.getMessage());
		}
	}
	
	@Override
	public void onTicker(TickerModel tickerModel, boolean hasChanged) {
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
