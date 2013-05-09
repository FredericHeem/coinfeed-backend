package com.coinfeed.marketfeed.core;

import static org.junit.Assert.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coinfeed.marketfeed.config.Config;
import com.coinfeed.marketfeed.config.ConfigReader;
import com.coinfeed.marketfeed.core.FeedManager;

public class FeedManagerTest {
	FeedManager feedManager;
	
	@Before
	public void setUp() throws Exception {
		feedManager = new FeedManager();
		Config config = ConfigReader.createFromFilename("config.dev.json");
		Assert.assertNotNull(config);
		feedManager.configure(config);
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
			countDownLatch.await(15, TimeUnit.SECONDS);
		}
		catch(Exception exception){
			fail(exception.getMessage());
		}
	}

}
