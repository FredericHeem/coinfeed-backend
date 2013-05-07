package com.coinfeed.marketfeed;


import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BitstampFeedDecoderTest {

	private static final String payloadOk = "{\"high\": \"114.90\", \"last\": \"107.80\", \"bid\": \"107.30\", \"volume\": \"16430.87667118\", \"low\": \"98.77\", \"ask\": \"107.80\"}";
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDecodeOk()
	{
		TickerModel tickerModel = BitstampFeedDecoder.decode(payloadOk);
		Assert.assertEquals(tickerModel.getBid(), "107.30");
	}
}
