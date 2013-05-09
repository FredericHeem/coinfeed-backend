package com.coinfeed.marketfeed.fetcher.mtgox;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.coinfeed.marketfeed.fetcher.DecoderException;
import com.coinfeed.marketfeed.fetcher.IFeedDecoder;
import com.coinfeed.marketfeed.model.TickerModel;

public class MtGoxFeedDecoder implements IFeedDecoder{
	 public TickerModel decode(String message) throws DecoderException{
		TickerModel tickerModel = new TickerModel();
		
		try {
			JSONObject jsonMessage = (JSONObject) JSONValue.parse(message);
			
			JSONObject data = (JSONObject)jsonMessage.get("data");
			if(data == null){
				throw new DecoderException("missing data");
			}
			
			//Bid
			Object bid = ((JSONObject)data.get("buy")).get("value");
			if(bid == null){
				throw new DecoderException("missing bid");
			}
			
			tickerModel.setBid(bid.toString());
			
			//Ask
			Object ask = ((JSONObject)data.get("sell")).get("value");
			if(ask == null){
				throw new DecoderException("missing ask");
			}
			
			tickerModel.setAsk(ask.toString());
			
			
		} catch (Exception e) {
			throw new DecoderException(e.getMessage());
		}
		return tickerModel;
	}
}
