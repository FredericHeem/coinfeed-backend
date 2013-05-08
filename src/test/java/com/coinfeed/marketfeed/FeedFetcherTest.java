package com.coinfeed.marketfeed;


import java.util.concurrent.CountDownLatch;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FeedFetcherTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testFetch()
	{
		final CountDownLatch countDownLatch = new CountDownLatch(4);
		
		FeedFetcher feedFetcher = new FeedFetcher(new FeedListener() {
			@Override
			public void onError(String error) {
				countDownLatch.countDown(); 
				Assert.assertTrue(false);
			}

			@Override
			public void onFeedFetch(TickerModel tickerModel) {
				countDownLatch.countDown(); 
			}
		});

		try {
			feedFetcher.start();
			countDownLatch.await();
		}
		catch(Exception exception){

		}
	}
}
