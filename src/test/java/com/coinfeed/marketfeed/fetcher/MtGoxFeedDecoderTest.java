package com.coinfeed.marketfeed.fetcher;


import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.Test;

import com.coinfeed.marketfeed.fetcher.DecoderException;
import com.coinfeed.marketfeed.fetcher.mtgox.MtGoxFeedDecoder;
import com.coinfeed.marketfeed.model.TickerModel;

public class MtGoxFeedDecoderTest {

	private static final String jsonOk = "{\"result\":\"success\",\"data\":{\"high\":{\"value\":\"116.77700\",\"value_int\":\"11677700\",\"display\":\"$116.78\",\"display_short\":\"$116.78\",\"currency\":\"USD\"},\"low\":{\"value\":\"106.00000\",\"value_int\":\"10600000\",\"display\":\"$106.00\",\"display_short\":\"$106.00\",\"currency\":\"USD\"},\"avg\":{\"value\":\"112.29826\",\"value_int\":\"11229826\",\"display\":\"$112.30\",\"display_short\":\"$112.30\",\"currency\":\"USD\"},\"vwap\":{\"value\":\"112.05252\",\"value_int\":\"11205252\",\"display\":\"$112.05\",\"display_short\":\"$112.05\",\"currency\":\"USD\"},\"vol\":{\"value\":\"87837.22111997\",\"value_int\":\"8783722111997\",\"display\":\"87,837.22\u00a0BTC\",\"display_short\":\"87,837.22\u00a0BTC\",\"currency\":\"BTC\"},\"last_local\":{\"value\":\"114.01499\",\"value_int\":\"11401499\",\"display\":\"$114.01\",\"display_short\":\"$114.01\",\"currency\":\"USD\"},\"last_orig\":{\"value\":\"114.01499\",\"value_int\":\"11401499\",\"display\":\"$114.01\",\"display_short\":\"$114.01\",\"currency\":\"USD\"},\"last_all\":{\"value\":\"114.01499\",\"value_int\":\"11401499\",\"display\":\"$114.01\",\"display_short\":\"$114.01\",\"currency\":\"USD\"},\"last\":{\"value\":\"114.01499\",\"value_int\":\"11401499\",\"display\":\"$114.01\",\"display_short\":\"$114.01\",\"currency\":\"USD\"},\"buy\":{\"value\":\"114.01000\",\"value_int\":\"11401000\",\"display\":\"$114.01\",\"display_short\":\"$114.01\",\"currency\":\"USD\"},\"sell\":{\"value\":\"114.01499\",\"value_int\":\"11401499\",\"display\":\"$114.01\",\"display_short\":\"$114.01\",\"currency\":\"USD\"},\"item\":\"BTC\",\"now\":\"1368043093947392\"}}";
	private static final String jsonMissingData = "{\"result\":\"ko\"}";
	@Test
	public void testDecodeOk()
	{
		TickerModel tickerModel;
		try {
			tickerModel = new MtGoxFeedDecoder().decode(jsonOk);
			Assert.assertEquals(tickerModel.getBid(), "114.01000");
			Assert.assertEquals(tickerModel.getAsk(), "114.01499");
		} catch (DecoderException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testMissingData()
	{
		try {
			new MtGoxFeedDecoder().decode(jsonMissingData);
			fail("KO");
		} catch (DecoderException e) {
		}
	}
}
