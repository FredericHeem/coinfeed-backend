package com.coinfeed.marketfeed.fetcher;

import com.coinfeed.marketfeed.model.TickerModel;

public interface IFeedDecoder {
	TickerModel decode(String message) throws DecoderException;
}
