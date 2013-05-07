package com.coinfeed.marketfeed;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class BitstampFeedDecoder {
	static TickerModel decode(String message){
		TickerModel tickerModel = new TickerModel();
		JSONObject jsonMessage = (JSONObject) JSONValue.parse(message);
		tickerModel.setBid(jsonMessage.get("bid").toString());
		return tickerModel;
	}
}
