package com.coinfeed.marketfeed;

import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class BitstampFeedDecoder {
	static TickerModel decode(String message){
		TickerModel tickerModel = new TickerModel();
		JSONObject jsonMessage = (JSONObject) JSONValue.parse(message);
		tickerModel.setBid(jsonMessage.get("bid").toString());
		tickerModel.setAsk(jsonMessage.get("ask").toString());
		tickerModel.setDate(new Date());
		return tickerModel;
	}
}
