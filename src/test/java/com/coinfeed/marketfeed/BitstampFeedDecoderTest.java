package com.coinfeed.marketfeed;


import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class BitstampFeedDecoderTest {

	private static final String payloadOk = "{\"high\": \"114.90\", \"last\": \"107.80\", \"bid\": \"107.30\", \"volume\": \"16430.87667118\", \"low\": \"98.77\", \"ask\": \"107.80\"}";

	@Test
	public void testDecodeOk()
	{
		TickerModel tickerModel;
		try {
			tickerModel = new BitstampFeedDecoder().decode(payloadOk);
			Assert.assertEquals(tickerModel.getBid(), "107.30");
			Assert.assertEquals(tickerModel.getAsk(), "107.80");
		} catch (DecoderException e) {
			fail(e.getMessage());
		}
		
	}
}
