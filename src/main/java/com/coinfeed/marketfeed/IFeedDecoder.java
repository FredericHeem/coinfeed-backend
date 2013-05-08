package com.coinfeed.marketfeed;

public interface IFeedDecoder {
	TickerModel decode(String message) throws DecoderException;
}
