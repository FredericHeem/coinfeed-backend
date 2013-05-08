package com.coinfeed.marketfeed;

public class MtGoxFeed extends FeedBase implements IFeed {
	//private static final Logger log = LoggerFactory.getLogger(MtGoxFeed.class);
	private static final String QUERY_MTGOX = "https://data.mtgox.com/api/2/BTCUSD/money/ticker";

	public MtGoxFeed(FeedListener feedListener) {
		super(feedListener);
		setQuery(QUERY_MTGOX);
		setMarketName("Mtgox-BTC-USD");
		setDecoder(new MtGoxFeedDecoder());
	}
}
