package com.coinfeed.marketfeed.store;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coinfeed.marketfeed.model.TickerModel;
import com.coinfeed.marketfeed.store.mongodb.FeedStore;
import com.coinfeed.marketfeed.store.mongodb.FeedStoreConfig;

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
				"coinfeed:coinfeed1234@ds059947.mongolab.com:59947/bitcointickers-dev");
	}
		
	@Test
	public void testAuthenticate(){
		FeedStore store = new FeedStore(config);
		store.setConfig(config);
		try {
			Assert.assertTrue(store.authenticate());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	//@Test
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
	
	//@Test
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
		FeedStore store = new FeedStore(config);
		try {
			Assert.assertTrue(store.authenticate());
			TickerModel tickerModel = TickerModel.create("Bitstamp-BTC-USD", "110", "111");
			store.write(tickerModel);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
}
