package com.coinfeed.marketfeed.fetcher;


import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import com.coinfeed.marketfeed.fetcher.DecoderException;
import com.coinfeed.marketfeed.fetcher.bitstamp.BitstampFeedDecoder;
import com.coinfeed.marketfeed.fetcher.justcoin.JustcoinFeedDecoder;
import com.coinfeed.marketfeed.model.TickerModel;

public class JustcoinFeedDecoderTest {
	private static final String payloadOk = "[{\"id\":\"BTCUSD\",\"last\":\"694.990\",\"high\":\"695.000\",\"low\":\"610.011\",\"bid\":\"690.000\",\"ask\":\"694.970\",\"volume\":\"56.35978\",\"scale\":3}]";
	private static final String jsonMissingBid = "[{\"id\":\"BTCUSD\",\"last\":\"694.990\",\"high\":\"695.000\",\"low\":\"610.011\",\"ask\":\"694.970\",\"volume\":\"56.35978\",\"scale\":3}]";
	private static final String jsonMissingAsk = "[{\"id\":\"BTCUSD\",\"last\":\"694.990\",\"high\":\"695.000\",\"low\":\"610.011\",\"bid\":\"690.000\",\"volume\":\"56.35978\",\"scale\":3}]";
	@Test
	public void testDecodeOk()
	{
		try {
			TickerModel tickerModel = new JustcoinFeedDecoder().decode(payloadOk);
			Assert.assertEquals(tickerModel.getBid(), "690.000");
			Assert.assertEquals(tickerModel.getAsk(), "694.970");
		} catch (DecoderException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testMissingBid()
	{
		try {
			new JustcoinFeedDecoder().decode(jsonMissingBid);
			fail("KO");
		} catch (DecoderException e) {
		}
	}
	
	@Test
	public void testMissingAsk()
	{
		try {
			new JustcoinFeedDecoder().decode(jsonMissingAsk);
			fail("KO");
		} catch (DecoderException e) {
		}
	}
}
