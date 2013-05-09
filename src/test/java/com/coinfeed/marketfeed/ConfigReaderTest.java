package com.coinfeed.marketfeed;


import static org.junit.Assert.fail;

import java.io.FileNotFoundException;

import junit.framework.Assert;

import org.junit.Test;

public class ConfigReaderTest {

	private static final String configContent = "{\"name\" : \"dev\",\"feeds\": [{}],\"stores\": [{\"name\": \"default\",\"dbName\": \"bitcointickers-dev\"}]}";

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
			Assert.assertNotNull(config.stores);
			FeedStoreConfig storeConfig = config.stores.get(0);
			Assert.assertNotNull(storeConfig);
			Assert.assertEquals(storeConfig.getHostname(), "ds059947.mongolab.com");
			Assert.assertEquals(storeConfig.getDbName(), "bitcointickers-dev");
		} catch (FileNotFoundException e) {
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
		}
	}
}
