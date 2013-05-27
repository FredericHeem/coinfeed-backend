package com.coinfeed.marketfeed.fetcher;


import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import com.coinfeed.marketfeed.fetcher.DecoderException;
import com.coinfeed.marketfeed.fetcher.bitstamp.BitstampFeedDecoder;
import com.coinfeed.marketfeed.model.TickerModel;

public class BitfinexFeedDecoderTest {

	private static final String payloadOk = "{\"mid\":\"125.155\",\"bid\":\"124.11\",\"ask\":\"126.2\",\"last_price\":\"124.01\",\"timestamp\":\"1369686967.096086032\"}";
	private static final String jsonMissingBid = "{\"mid\":\"125.155\",\"ask\":\"126.2\",\"last_price\":\"124.01\",\"timestamp\":\"1369686967.096086032\"}";
	private static final String jsonMissingAsk = "{\"mid\":\"125.155\",\"bid\":\"124.11\",\"last_price\":\"124.01\",\"timestamp\":\"1369686967.096086032\"}";
	@Test
	public void testDecodeOk()
	{
		try {
			TickerModel tickerModel = new BitstampFeedDecoder().decode(payloadOk);
			Assert.assertEquals(tickerModel.getBid(), "124.11");
			Assert.assertEquals(tickerModel.getAsk(), "126.2");
		} catch (DecoderException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testMissingBid()
	{
		try {
			new BitstampFeedDecoder().decode(jsonMissingBid);
			fail("KO");
		} catch (DecoderException e) {
		}
	}
	
	@Test
	public void testMissingAsk()
	{
		try {
			new BitstampFeedDecoder().decode(jsonMissingAsk);
			fail("KO");
		} catch (DecoderException e) {
		}
	}
}
