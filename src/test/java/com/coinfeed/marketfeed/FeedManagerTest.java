package com.coinfeed.marketfeed;

import static org.junit.Assert.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FeedManagerTest {
	FeedManager feedManager;
	
	@Before
	public void setUp() throws Exception {
		feedManager = new FeedManager();
		Assert.assertTrue(feedManager.initialize());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStartFetch() {
	    final CountDownLatch countDownLatch = new CountDownLatch(2);
		try {
			feedManager.startFetch();
			countDownLatch.await(25000, TimeUnit.MILLISECONDS);
		}
		catch(Exception exception){
			fail(exception.getMessage());
		}
	}

}
