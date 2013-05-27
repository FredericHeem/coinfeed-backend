package com.coinfeed.marketfeed.config;


import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import junit.framework.Assert;

import org.junit.Test;

import com.coinfeed.marketfeed.config.Config;
import com.coinfeed.marketfeed.config.ConfigReader;
import com.coinfeed.marketfeed.fetcher.FeedPollerConfig;
import com.coinfeed.marketfeed.store.FeedStoreConfig;

public class ConfigReaderTest {

	private static final String configContent = "{\"name\" : \"dev\",\"feeds\": [{}],\"stores\": [{\"name\": \"default\",\"dbName\": \"bitcointickers-dev\"}]}";
	private static final String jsonKO = "asasa";
	@Test
	public void testConfigParse(){
		Config config = ConfigReader.createFromString(configContent);
		Assert.assertNotNull(config);
		Assert.assertEquals(config.name, "dev");
		Assert.assertNotNull(config.stores);
		FeedStoreConfig storeConfig = config.stores.get(0);
		Assert.assertNotNull(storeConfig);
		Assert.assertEquals(storeConfig.getDbName(), "bitcointickers-dev");
	}
	
	@Test
	public void createFromFilenameDev(){
		try {
			Config config = ConfigReader.createFromFilename("config.dev.json");
			Assert.assertNotNull(config);
			Assert.assertEquals(config.name, "dev");
			//Fetcher
			Assert.assertNotNull(config.fetchers);
			Assert.assertEquals(config.fetchers.size(), 4);
			FeedPollerConfig fetcherConfigBitstamp = config.fetchers.get(0);
			Assert.assertEquals(fetcherConfigBitstamp.getName(), "Bitstamp-BTC-USD");
			Assert.assertEquals(fetcherConfigBitstamp.getPollingPeriod(), 5000);
			
			FeedPollerConfig fetcherConfigMtGox = config.fetchers.get(1);
			Assert.assertEquals(fetcherConfigMtGox.getName(), "MtGox-BTC-USD");
			
			FeedPollerConfig fetcherConfigBtce = config.fetchers.get(2);
			Assert.assertEquals(fetcherConfigBtce.getName(), "Btce-BTC-USD");
			
			FeedPollerConfig fetcherConfigBitfinex = config.fetchers.get(3);
			Assert.assertEquals(fetcherConfigBitfinex.getName(), "Bitfinex-BTC-USD");
			//Stores
			Assert.assertNotNull(config.stores);
			FeedStoreConfig storeConfig = config.stores.get(0);
			Assert.assertNotNull(storeConfig);
			Assert.assertEquals(storeConfig.getHostname(), "ds059947.mongolab.com");
			Assert.assertEquals(storeConfig.getDbName(), "bitcointickers-dev");
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void createFromFilenameProd(){
		try {
			Config config = ConfigReader.createFromFilename("config.prod.json");
			Assert.assertNotNull(config);
			Assert.assertEquals(config.name, "prod");
			Assert.assertNotNull(config.stores);
			Assert.assertEquals(config.stores.size(), 1);
			FeedStoreConfig storeConfig = config.stores.get(0);
			Assert.assertNotNull(storeConfig);
			Assert.assertEquals(storeConfig.getHostname(), "ds061797.mongolab.com");
			Assert.assertEquals(storeConfig.getDbName(), "bitcointickers");
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testConfigKO(){
		Config config = ConfigReader.createFromString(jsonKO);
		Assert.assertNull(config);
	}

	@Test
	public void testConfigFromFileNameKO(){
		Config config = ConfigReader.createFromString("src/test/resources/config.ko.json");
		Assert.assertNull(config);
	}
}
