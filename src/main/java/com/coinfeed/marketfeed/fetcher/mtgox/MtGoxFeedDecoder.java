package com.coinfeed.marketfeed.fetcher.mtgox;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coinfeed.marketfeed.fetcher.DecoderException;
import com.coinfeed.marketfeed.fetcher.FeedBaseTest;
import com.coinfeed.marketfeed.fetcher.IFeedDecoder;
import com.coinfeed.marketfeed.model.TickerModel;

public class MtGoxFeedDecoder implements IFeedDecoder {
	private static final Logger log = LoggerFactory.getLogger(FeedBaseTest.class);

	public TickerModel decode(String message) throws DecoderException {
		TickerModel tickerModel = new TickerModel();

		try {
			JSONObject jsonMessage = (JSONObject) JSONValue.parse(message);

			JSONObject data = (JSONObject) jsonMessage.get("data");
			if (data == null) {
				throw new DecoderException("missing data");
			}

			// Bid
			Object bid = ((JSONObject) data.get("buy")).get("value");
			tickerModel.setBid(bid.toString());

			// Ask
			Object ask = ((JSONObject) data.get("sell")).get("value");
			tickerModel.setAsk(ask.toString());

		} catch (Exception e) {
			log.error("decode error, messge: " + message);
			throw new DecoderException(e.getMessage());
		}
		return tickerModel;
	}
}
