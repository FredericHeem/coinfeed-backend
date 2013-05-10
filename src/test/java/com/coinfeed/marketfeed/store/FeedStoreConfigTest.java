package com.coinfeed.marketfeed.store;

import org.junit.Assert;
import org.junit.Test;

import com.coinfeed.marketfeed.store.FeedStoreConfig;

public class FeedStoreConfigTest {

	@Test
	public void testURI(){
		FeedStoreConfig config = new FeedStoreConfig();
		config.setHostname("ds12345.mongolab.com");
		config.setDbName("mydbname");
		config.setPort(59947);
		config.setUsername("myusername");
		config.setPassword("mypassword");
		Assert.assertEquals(config.toString(),
				"myusername:mypassword@ds12345.mongolab.com:59947/mydbname");
	}
}
