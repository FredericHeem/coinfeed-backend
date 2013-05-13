package com.coinfeed.marketfeed.fetcher.btce;


import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.Test;

import com.coinfeed.marketfeed.fetcher.DecoderException;
import com.coinfeed.marketfeed.model.TickerModel;

public class BtceFeedDecoderTest {

	private static final String jsonOk = "{\"ticker\":{\"high\":114.012,\"low\":109.94,\"avg\":111.976,\"vol\":445330.82681,\"vol_cur\":3959.39308,\"last\":112.731,\"buy\":112.731,\"sell\":112.71,\"server_time\":1368472265}}";
	private static final String jsonMissingData = "{\"result\":\"ko\"}";
	@Test
	public void testDecodeOk()
	{
		TickerModel tickerModel;
		try {
			tickerModel = new BtceFeedDecoder().decode(jsonOk);
			Assert.assertEquals(tickerModel.getBid(), "112.71");
			Assert.assertEquals(tickerModel.getAsk(), "112.731");
		} catch (DecoderException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testMissingData()
	{
		try {
			new BtceFeedDecoder().decode(jsonMissingData);
			fail("KO");
		} catch (DecoderException e) {
		}
	}
}
