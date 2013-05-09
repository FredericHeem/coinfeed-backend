package com.coinfeed.marketfeed;

import org.junit.Test;

public class MarketFeedAppTest {
	@Test
	public void runDevConfig()
	{
		String[] args =  new String[1];
		args[0] = "config.dev.json";
		
		MarketFeedApp app = new MarketFeedApp();
		app.run(app.createConfig(args), 10);
	}
	
	@Test
	public void runDevConfigNotFound()
	{
		String[] args =  new String[1];
		args[0] = "configdoesnnot exist.dev.json";
		MarketFeedApp.main(args);
	}
}