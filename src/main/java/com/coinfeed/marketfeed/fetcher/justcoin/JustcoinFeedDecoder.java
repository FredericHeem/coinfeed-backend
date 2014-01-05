package com.coinfeed.marketfeed.fetcher.justcoin;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;

import com.coinfeed.marketfeed.fetcher.DecoderException;
import com.coinfeed.marketfeed.fetcher.IFeedDecoder;
import com.coinfeed.marketfeed.model.TickerModel;

public class JustcoinFeedDecoder implements IFeedDecoder {
	@Override
	public TickerModel decode(String message) throws DecoderException{
		TickerModel tickerModel = new TickerModel();
		Object obj = JSONValue.parse(message);
		JSONArray jsonMessage = (JSONArray) obj;
		
		for (int i = 0; i < jsonMessage.size(); i++) {  // **line 2**
			JSONObject childJSONObject = (JSONObject)jsonMessage.get(i);
			String market = (String)childJSONObject.get("id");
			if(market.compareTo("BTCUSD") == 0){
				//Bid
				Object bid = childJSONObject.get("bid");
				if(bid == null){
					throw new DecoderException("missing bid");
				}

				tickerModel.setBid(bid.toString());

				//Ask
				Object ask = childJSONObject.get("ask");
				if(ask == null){
					throw new DecoderException("missing ask");
				}

				tickerModel.setAsk(ask.toString());
			}
		}

		return tickerModel;
	}
}
