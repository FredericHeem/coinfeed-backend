package com.coinfeed.marketfeed;


import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FeedStoreTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testURI(){
		FeedStoreConfig config = new FeedStoreConfig();
		Assert.assertEquals(config.toString(),
				"coinfeed:coinfeed1234@ds061797.mongolab.com:61797/bitcointickers");
	}
	
	@Test
	public void testAuthenticate(){
		FeedStoreConfig config = new FeedStoreConfig();
		FeedStore store = new FeedStore(config);
		try {
			Assert.assertTrue(store.authenticate());
		} catch (UnknownHostException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
}
