package com.coinfeed.marketfeed.core;

import static org.junit.Assert.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.Test;

import com.coinfeed.marketfeed.app.FeedManagerException;
import com.coinfeed.marketfeed.config.Config;
import com.coinfeed.marketfeed.config.ConfigReader;
import com.coinfeed.marketfeed.core.FeedManager;

public class FeedManagerTest {
	FeedManager feedManager;

	private void testConfigError(String configFile, String errorType){
		try {
			Config config;
			config = ConfigReader.createFromFilename(configFile);
			Assert.assertNotNull(config);
			feedManager = new FeedManager(config);
			feedManager.configure();
			feedManager.initialize();
			fail("should have thrown an exception");
		} catch (FeedManagerException e) {
			Assert.assertEquals(e.getType(), errorType);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testConfigEmptyFetcher()
	{
		testConfigError("src/test/resources/config.empty-fetchers.json",
				FeedManagerException.CONFIGURATION);
	}
	
	@Test
	public void testConfigEmptyStore()
	{
		testConfigError("src/test/resources/config.empty-stores.json",
				FeedManagerException.CONFIGURATION);
	}
	
	@Test
	public void testConfigDbAuthBadPassword()
	{
		testConfigError("src/test/resources/config.db-auth-bad-password.json",
				FeedManagerException.DB_AUTHENTICATION);
	}
	
	@Test
	public void testConfigDbAuthBadUsername()
	{
		testConfigError("src/test/resources/config.db-auth-bad-username.json",
				FeedManagerException.DB_AUTHENTICATION);
	}
	
	@Test
	public void testConfigDbBadHostname()
	{
		testConfigError("src/test/resources/config.db-bad-hostname.json",
				FeedManagerException.DB_AUTHENTICATION);
	}

	@Test
	public void testConfigDbName()
	{
		testConfigError("src/test/resources/config.db-bad-dbname.json",
				FeedManagerException.DB_AUTHENTICATION);
	}

	@Test
	public void testConfigFetcherBadDriver()
	{
		testConfigError("src/test/resources/config.fetcher-bad-driver.json",
				FeedManagerException.CONFIGURATION);
	}
	
	@Test
	public void testConfigStoreBadDriver()
	{
		testConfigError("src/test/resources/config.store-bad-driver.json",
				FeedManagerException.CONFIGURATION);
	}

	//@Test
	public void testStartFetch() {
	    final CountDownLatch countDownLatch = new CountDownLatch(2);
		try {
			Config config = ConfigReader.createFromFilename("config.dev.json");
			Assert.assertNotNull(config);
			feedManager = new FeedManager(config);
			feedManager.configure();
			feedManager.initialize();
			feedManager.startFetch();
			countDownLatch.await(15, TimeUnit.SECONDS);
		}
		catch(Exception exception){
			fail(exception.getMessage());
		}
	}

}
