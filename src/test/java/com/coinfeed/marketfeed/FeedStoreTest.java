package com.coinfeed.marketfeed;


import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FeedStoreTest {
	private FeedStoreConfig config = new FeedStoreConfig();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testURI(){
		Assert.assertEquals(config.toString(),
				"coinfeed:coinfeed1234@ds061797.mongolab.com:61797/bitcointickers");
	}
		
	@Test
	public void testAuthenticate(){
		FeedStore store = new FeedStore(config);
		try {
			Assert.assertTrue(store.authenticate());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testAuthenticateKOHostname(){
		config.setHostname("noexisting.com");
		FeedStore store = new FeedStore(config);
		try {
			Assert.assertFalse(store.authenticate());
		} catch (Exception e) {
		}
	}
	
	
	@Test
	public void testNeedAuthentication(){
		FeedStore store = new FeedStore(config);
		try {
			store.getDatabaseNames();
			Assert.assertTrue(false);
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testAuthenticateKOPort(){
		config.setPort(12345);
		FeedStore store = new FeedStore(config);
		try {
			boolean authenticated = store.authenticate();
			Assert.assertFalse(authenticated);
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testWrite(){
		FeedStoreConfig config = new FeedStoreConfig();
		FeedStore store = new FeedStore(config);
		try {
			Assert.assertTrue(store.authenticate());
			TickerModel tickerModel = new TickerModel();
			tickerModel.setMarketName("Bitstamp");
			tickerModel.setBid("110");
			tickerModel.setAsk("111");
			store.write(tickerModel);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
}
