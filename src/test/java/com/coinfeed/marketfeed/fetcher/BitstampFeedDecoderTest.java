package com.coinfeed.marketfeed.fetcher;


import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import com.coinfeed.marketfeed.fetcher.DecoderException;
import com.coinfeed.marketfeed.fetcher.bitstamp.BitstampFeedDecoder;
import com.coinfeed.marketfeed.model.TickerModel;

public class BitstampFeedDecoderTest {

	private static final String payloadOk = "{\"high\": \"114.90\", \"last\": \"107.80\", \"bid\": \"107.30\", \"volume\": \"16430.87667118\", \"low\": \"98.77\", \"ask\": \"107.80\"}";
	private static final String jsonMissingBid = "{\"high\": \"114.90\", \"last\": \"107.80\", \"volume\": \"16430.87667118\", \"low\": \"98.77\", \"ask\": \"107.80\"}";
	private static final String jsonMissingAsk = "{\"high\": \"114.90\", \"last\": \"107.80\", \"bid\": \"107.30\", \"volume\": \"16430.87667118\", \"low\": \"98.77\"}";
	@Test
	public void testDecodeOk()
	{
		try {
			TickerModel tickerModel = new BitstampFeedDecoder().decode(payloadOk);
			Assert.assertEquals(tickerModel.getBid(), "107.30");
			Assert.assertEquals(tickerModel.getAsk(), "107.80");
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
