package com.coinfeed.marketfeed.app;

import org.junit.Test;

import com.coinfeed.marketfeed.app.MarketFeedApp;

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
	
	@Test
	public void runNoConfig()
	{
		String[] args =  new String[0];
		MarketFeedApp.main(args);
	}
}
