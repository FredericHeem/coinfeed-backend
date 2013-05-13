package com.coinfeed.marketfeed.fetcher.btce;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coinfeed.marketfeed.fetcher.DecoderException;
import com.coinfeed.marketfeed.fetcher.IFeedDecoder;
import com.coinfeed.marketfeed.model.TickerModel;

public class BtceFeedDecoder implements IFeedDecoder {
	private static final Logger log = LoggerFactory.getLogger(BtceFeedDecoder.class);

	public TickerModel decode(String message) throws DecoderException {
		TickerModel tickerModel = new TickerModel();

		try {
			JSONObject jsonMessage = (JSONObject) JSONValue.parse(message);

			JSONObject data = (JSONObject) jsonMessage.get("ticker");
			if (data == null) {
				throw new DecoderException("missing ticker");
			}

			// Bid
			Object bid = data.get("sell");
			tickerModel.setBid(bid.toString());

			// Ask
			Object ask = data.get("buy");
			tickerModel.setAsk(ask.toString());

		} catch (Exception e) {
			log.error("decode error, messge: " + message);
			throw new DecoderException(e.getMessage());
		}
		return tickerModel;
	}
}
