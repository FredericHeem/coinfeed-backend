package com.coinfeed.marketfeed.fetcher.bitstamp;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.coinfeed.marketfeed.fetcher.DecoderException;
import com.coinfeed.marketfeed.fetcher.IFeedDecoder;
import com.coinfeed.marketfeed.model.TickerModel;

public class BitstampFeedDecoder implements IFeedDecoder {
	@Override
	public TickerModel decode(String message) throws DecoderException{
		TickerModel tickerModel = new TickerModel();
		JSONObject jsonMessage = (JSONObject) JSONValue.parse(message);
		//Bid
		Object bid = jsonMessage.get("bid");
		if(bid == null){
			throw new DecoderException("missing bid");
		}
		
		tickerModel.setBid(bid.toString());
		
		//Ask
		Object ask = jsonMessage.get("ask");
		if(ask == null){
			throw new DecoderException("missing ask");
		}
		
		tickerModel.setAsk(ask.toString());
		return tickerModel;
	}
}
