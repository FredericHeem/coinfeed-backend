package com.coinfeed.marketfeed;


import java.util.concurrent.CountDownLatch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BitstampFeedTest {

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
		
		BitstampFeed bitstampFeed = new BitstampFeed(new FeedListener() {
			@Override
			public void onError(String error) {
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
